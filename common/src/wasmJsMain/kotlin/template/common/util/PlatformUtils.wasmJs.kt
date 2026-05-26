package template.common.util

import kotlinx.browser.document

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        val lang = if (code.isEmpty()) "en" else code
        document.documentElement?.setAttribute("lang", lang)
        println("PlatformUtils Web: Language set to '$lang'")
    }

    actual fun changeTheme(isDark: Boolean) {
        println("PlatformUtils Web: changeTheme to isDark=$isDark")
    }
}
