package template.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
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
) {
    val languageDataStore: LanguageDataStore = koinInject()
    
    // 1. Initialize LanguageManager
    SideEffect {
        template.common.util.LanguageManager.init(languageDataStore)
    }

    // 2. Observe the centralized state
    val languageState by template.common.util.LanguageManager.currentLanguage.collectAsState()

    // 3. Wait for the state to transition away from UNKNOWN before rendering
    if (languageState == Language.UNKNOWN) {
        println("App: Waiting for LanguageManager initialization...")
        return
    }

    val languageCode = remember(languageState) {
        when (languageState) {
            Language.ENGLISH -> "en"
            Language.JAPANESE -> "ja"
            Language.BENGALI -> "bn"
            Language.SYSTEM -> ""
            else -> "" // Fallback for UNKNOWN (though we check above)
        }
    }

    println("App: Render languageState=$languageState -> code='$languageCode'")

    template.common.util.ProvideAppLocale(languageCode) {
        key(languageCode) {
            KoinContext {
                val themeLocalDataStore: ThemeLocalDataStore = koinInject()
                val themeMode by themeLocalDataStore.themeMode.collectAsState(initial = ThemeMode.SYSTEM)
                val isDarkTheme = when (themeMode) {
                    ThemeMode.DARK -> true
                    ThemeMode.LIGHT -> false
                    else -> isSystemInDarkTheme()
                }

                LaunchedEffect(languageCode) {
                    onLanguageChange(languageCode)
                }

                TemplateTheme(useDarkTheme = isDarkTheme) {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        MainAnimationNavHost()
                    }
                }
            }
        }
    }
}
