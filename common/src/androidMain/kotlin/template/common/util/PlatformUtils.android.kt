package template.common.util

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        val appLocale = LocaleListCompat.forLanguageTags(code)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}
