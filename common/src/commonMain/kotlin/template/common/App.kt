package template.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import template.common.ui.MainAnimationNavHost
import template.storage.local.language.LanguageDataStore
import template.storage.local.theme.ThemeLocalDataStore
import template.storage.local.theme.ThemeMode
import template.theme.TemplateTheme
import template.theme.splashScreen.SplashViewModel
import androidx.compose.runtime.key

@Composable
fun App(
    onLanguageChange: suspend (String) -> Unit = {}
) {
    KoinContext {
        val splashViewModel: SplashViewModel = koinInject()

        LaunchedEffect(splashViewModel) {
            // You can add logic here if needed for splash exit
        }

        val themeLocalDataStore: ThemeLocalDataStore = koinInject()
        val languageDataStore: LanguageDataStore = koinInject()

        val themeMode by themeLocalDataStore.themeMode
            .collectAsState(initial = ThemeMode.SYSTEM)

        val languageState by languageDataStore.getLanguage
            .collectAsState(initial = null)

        val isDarkTheme = when (themeMode) {
            ThemeMode.DARK -> true
            ThemeMode.LIGHT -> false
            ThemeMode.SYSTEM -> isSystemInDarkTheme()
        }

        LaunchedEffect(languageState) {
            languageState?.let { lang ->
                val code = when (lang) {
                    template.storage.local.language.Language.SYSTEM -> ""
                    template.storage.local.language.Language.ENGLISH -> "en"
                    template.storage.local.language.Language.JAPANESE -> "ja"
                    template.storage.local.language.Language.BENGALI -> "bn"
                }
                onLanguageChange(code)
            }
        }

        languageState?.let { language ->
            key(language) {
                TemplateTheme(useDarkTheme = isDarkTheme) {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        MainAnimationNavHost()
                    }
                }
            }
        }
    }
}
