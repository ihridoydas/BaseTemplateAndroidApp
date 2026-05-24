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
    KoinContext {
        val themeLocalDataStore: ThemeLocalDataStore = koinInject()
        val languageDataStore: LanguageDataStore = koinInject()

        // Theme management
        val themeMode by themeLocalDataStore.themeMode.collectAsState(initial = ThemeMode.SYSTEM)
        val isDarkTheme = when (themeMode) {
            ThemeMode.DARK -> true
            ThemeMode.LIGHT -> false
            else -> isSystemInDarkTheme()
        }

        // Language management
        val languageState by languageDataStore.getLanguage.collectAsState(initial = null)

        // Ensure we wait for the first DataStore emission
        if (languageState == null) return@KoinContext

        val languageCode = when (languageState) {
            Language.ENGLISH -> "en"
            Language.JAPANESE -> "ja"
            Language.BENGALI -> "bn"
            Language.SYSTEM -> ""
            else -> ""
        }

        println("App: State is $languageState, Applying code '$languageCode'")

        // Side effect for platform persistence
        LaunchedEffect(languageCode) {
            onLanguageChange(languageCode)
        }

        // 🔹 Key ensures total UI reload on language change
        key(languageCode) {
            template.common.util.ProvideAppLocale(languageCode) {
                TemplateTheme(useDarkTheme = isDarkTheme) {
                    Surface(color = MaterialTheme.colorScheme.background) {
                        MainAnimationNavHost()
                    }
                }
            }
        }
    }
}
