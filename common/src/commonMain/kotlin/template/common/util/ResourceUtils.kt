@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package template.common.util

import androidx.compose.runtime.*
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.platform.LocalProvidableLocaleList
import org.jetbrains.compose.resources.*
import org.jetbrains.compose.resources.ThemeQualifier

@Composable
fun ProvideAppLocale(
    languageCode: String,
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    println("ProvideAppLocale: languageCode = '$languageCode', isDarkTheme = $isDarkTheme")
    // 1. Force the Compose Resources environment to use the given language and theme
    val systemEnv = getSystemResourceEnvironment()
    val customEnv = remember(languageCode, isDarkTheme, systemEnv) {
        ResourceEnvironment(
            language = if (languageCode.isEmpty()) systemEnv.language else LanguageQualifier(languageCode),
            region = systemEnv.region,
            theme = if (isDarkTheme) ThemeQualifier.DARK else ThemeQualifier.LIGHT,
            density = systemEnv.density
        )
    }

    val customComposeEnvironment = remember(customEnv) {
        object : ComposeEnvironment {
            @Composable
            override fun rememberEnvironment(): ResourceEnvironment {
                println("ProvideAppLocale: ComposeEnvironment.rememberEnvironment returning ${customEnv.language.language}")
                return customEnv
            }
        }
    }

    // 2. Update the standard Compose LocaleList for other components
    val localeList = remember(languageCode) {
        if (languageCode.isEmpty()) LocaleList.current else LocaleList(Locale(languageCode))
    }

    CompositionLocalProvider(
        LocalComposeEnvironment provides customComposeEnvironment,
        LocalProvidableLocaleList provides localeList
    ) {
        content()
    }
}
