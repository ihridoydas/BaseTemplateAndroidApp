package template.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import template.common.ui.MainAnimationNavHost
import template.storage.local.language.Language
import template.storage.local.language.LanguageDataStore
import template.storage.local.theme.ThemeLocalDataStore
import template.storage.local.theme.ThemeMode
import template.theme.TemplateTheme

@Composable
fun App(
    onLanguageChange: (String) -> Unit = {},
    onThemeChange: (Boolean) -> Unit = {},
) {
    KoinContext {
        val languageDataStore: LanguageDataStore = koinInject()
        val themeLocalDataStore: ThemeLocalDataStore = koinInject()
        
        // 1. Initialize LanguageManager
        SideEffect {
            template.common.util.LanguageManager.init(languageDataStore)
        }

        // 2. Observe the centralized states
        val languageState by template.common.util.LanguageManager.currentLanguage.collectAsState()
        val themeMode by themeLocalDataStore.themeMode.collectAsState(initial = ThemeMode.SYSTEM)

        // 3. Wait for the state to transition away from UNKNOWN before rendering
        if (languageState == Language.UNKNOWN) {
            return@KoinContext
        }

        val languageCode = remember(languageState) {
            when (languageState) {
                Language.ENGLISH -> "en"
                Language.JAPANESE -> "ja"
                Language.BENGALI -> "bn"
                Language.SYSTEM -> ""
                else -> ""
            }
        }

        val isDarkTheme = when (themeMode) {
            ThemeMode.DARK -> true
            ThemeMode.LIGHT -> false
            else -> isSystemInDarkTheme()
        }

        LaunchedEffect(languageCode) {
            onLanguageChange(languageCode)
        }

        LaunchedEffect(isDarkTheme) {
            onThemeChange(isDarkTheme)
        }

        template.common.util.ProvideAppLocale(languageCode, isDarkTheme) {
            TemplateTheme(useDarkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    key(languageCode) {
                        MainAnimationNavHost()
                    }
                }
            }
        }
    }
}
