package template.common.util

import platform.Foundation.NSUserDefaults

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        val defaults = NSUserDefaults.standardUserDefaults
        val languages = if (code.isEmpty()) null else listOf(code)
        defaults.setObject(languages, "AppleLanguages")
        defaults.synchronize()
    }
}
