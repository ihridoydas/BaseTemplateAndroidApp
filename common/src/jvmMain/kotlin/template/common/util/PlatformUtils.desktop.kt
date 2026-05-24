package template.common.util

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        val locale = if (code.isEmpty()) java.util.Locale.getDefault() else java.util.Locale.forLanguageTag(code)
        java.util.Locale.setDefault(locale)
    }
}
