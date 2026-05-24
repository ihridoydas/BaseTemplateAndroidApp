@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package template.common.util

import androidx.compose.runtime.*
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.platform.LocalProvidableLocaleList
import org.jetbrains.compose.resources.*

@Composable
fun ProvideAppLocale(
    languageCode: String,
    content: @Composable () -> Unit
) {
    // 1. Force the Compose Resources environment to use the given language
    // We create a custom ComposeEnvironment that overrides the language qualifier.
    val customComposeEnvironment = remember(languageCode) {
        object : ComposeEnvironment {
            @Composable
            override fun rememberEnvironment(): ResourceEnvironment {
                val systemEnv = getSystemResourceEnvironment()
                return if (languageCode.isEmpty()) {
                    systemEnv
                } else {
                    ResourceEnvironment(
                        language = LanguageQualifier(languageCode),
                        region = systemEnv.region,
                        theme = systemEnv.theme,
                        density = systemEnv.density
                    )
                }
            }
        }
    }

    // 2. Update the standard Compose LocaleList for other components
    val localeList = remember(languageCode) {
        if (languageCode.isEmpty()) {
            LocaleList.current
        } else {
            LocaleList(Locale(languageCode))
        }
    }

    CompositionLocalProvider(
        LocalComposeEnvironment provides customComposeEnvironment,
        LocalProvidableLocaleList provides localeList
    ) {
        content()
    }
}
