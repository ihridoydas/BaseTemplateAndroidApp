package template.common.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        val appLocale = LocaleListCompat.forLanguageTags(code)
        if (AppCompatDelegate.getApplicationLocales() != appLocale) {
            AppCompatDelegate.setApplicationLocales(appLocale)
        }
        // Force immediate update of resources for Compose Multiplatform
        val locale = if (code.isEmpty()) java.util.Locale.getDefault() else java.util.Locale.forLanguageTag(code)
        if (java.util.Locale.getDefault() != locale) {
            java.util.Locale.setDefault(locale)
        }
    }

    actual fun changeTheme(isDark: Boolean) {
        val mode = if (isDark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        if (AppCompatDelegate.getDefaultNightMode() != mode) {
            AppCompatDelegate.setDefaultNightMode(mode)
        }
    }
}
