package template.common.util

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        println("PlatformUtils Desktop: changeLanguage to $code")
        val locale = if (code.isEmpty()) java.util.Locale.getDefault() else java.util.Locale.forLanguageTag(code)
        java.util.Locale.setDefault(locale)
    }
}
