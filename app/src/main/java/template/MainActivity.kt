/*
* MIT License
*
* Copyright (c) 2024 Hridoy Chandra Das
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package template

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.xr.runtime.Session
import androidx.xr.runtime.SessionCreateSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import template.common.DURATION
import template.common.VALUES_X
import template.common.VALUES_Y
import template.common.utils.RootUtil
import template.datastore.ThemePreferences
import template.local.language.LanguageDataStore
import template.local.theme.ThemeLocalDataStore
import template.theme.TemplateTheme
import template.theme.splashScreen.SplashViewModel
import template.ui.MainAnimationNavHost
import template.util.Utils
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private val Tag = MainActivity::class.java.simpleName
    }

    private val splashViewModel: SplashViewModel by viewModels()
    private lateinit var languageDataStore: LanguageDataStore
    private lateinit var themeDataStore: ThemeLocalDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize DataStores immediately to prevent UninitializedPropertyAccessException
        languageDataStore = LanguageDataStore(this)
        themeDataStore = ThemeLocalDataStore(this)

        configureEdgeToEdgeWindow()

        // Check Rooted Device
        if (RootUtil.isDeviceRooted()) {
            Timber.tag(Tag).e("onCreate - Rooted device.")
            finish()
            return
        }

        Timber.tag(Tag).d("onCreate")

        // XR Session Initialization Guard
        if (packageManager.hasSystemFeature("android.software.xr.immersive") ||
            packageManager.hasSystemFeature("com.google.android.xr.heritage")
        ) {
            lifecycleScope.launch {
                try {
                    val result = withContext(Dispatchers.IO) {
                        Session.create(this@MainActivity, coroutineContext, this@MainActivity)
                    }
                    if (result is SessionCreateSuccess) {
                        val xrSession = result.session
                        Timber.tag(Tag).d("XR Session created successfully")
                    } else {
                        Timber.tag(Tag).w("XR Session creation failed or not supported: %s", result)
                    }
                } catch (e: Exception) {
                    Timber.tag(Tag).e(e, "Error during XR Session creation")
                }
            }
        } else {
            Timber.tag(Tag).d("XR not supported on this device")
        }

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !splashViewModel.isLoading.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    VALUES_X,
                    VALUES_Y,
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = DURATION
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    VALUES_X,
                    VALUES_Y,
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = DURATION
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }

        enableEdgeToEdge()

        runBlocking {
            val language = languageDataStore.getLanguage.first()
            Utils.applyLanguage(this@MainActivity, language)
        }

        setContent {
            val themeMode by themeDataStore.themeMode
                .collectAsState(initial = ThemePreferences.ThemeMode.SYSTEM)

            val isDarkTheme = when (themeMode) {
                ThemePreferences.ThemeMode.DARK -> true
                ThemePreferences.ThemeMode.LIGHT -> false
                ThemePreferences.ThemeMode.SYSTEM -> isSystemInDarkTheme()
                else -> false
            }
            TemplateTheme(useDarkTheme = isDarkTheme) {
                ChangeSystemBarsTheme(!isDarkTheme)
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainAnimationNavHost(languageDataStore, themeDataStore)
                }
            }
        }
    }

    /**
     * Configures our [MainActivity] window so that it reaches edge to edge of the device, meaning
     * content can be rendered underneath the status and navigation bars.
     *
     * This method works hand in hand with [ConfigureTransparentSystemBars], to make sure content
     * behind these bars is visible.
     *
     * Keep in mind that if you need to make sure your content padding doesn't clash with the status bar text/icons,
     * you can leverage modifiers like `windowInsetsPadding()` and `systemBarsPadding()`. For more information,
     * read the Compose WindowInsets docs: https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/WindowInsets
     */
    private fun configureEdgeToEdgeWindow() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    @Composable
    private fun ChangeSystemBarsTheme(lightTheme: Boolean) {
        val barColor = MaterialTheme.colorScheme.background.toArgb()
        LaunchedEffect(lightTheme) {
            if (lightTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        barColor,
                        barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.light(
                        barColor,
                        barColor,
                    ),
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                )
            }
        }
    }
}
