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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.android.ext.android.get
import template.common.App
import template.common.utils.RootUtil
import template.theme.splashScreen.SplashViewModel
import timber.log.Timber
import template.common.util.PlatformUtils

class MainActivity : AppCompatActivity() {
    companion object {
        private val Tag = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashViewModel: SplashViewModel = get()
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }

        super.onCreate(savedInstanceState)

//        // Check Rooted Device
//        if (RootUtil.isDeviceRooted()) {
//            Timber.tag(Tag).e("onCreate - Rooted device.")
//            finish()
//            return
//        }

        Timber.tag(Tag).d("onCreate")

        enableEdgeToEdge()

        setContent {
            App(
                onLanguageChange = { code ->
                    PlatformUtils.changeLanguage(code)
                },
                onThemeChange = { isDark ->
                    PlatformUtils.changeTheme(isDark)
                }
            )
        }
    }
}
