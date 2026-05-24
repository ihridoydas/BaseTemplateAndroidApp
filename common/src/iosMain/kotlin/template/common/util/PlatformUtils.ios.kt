package template.common.util

import platform.Foundation.NSUserDefaults

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        val defaults = NSUserDefaults.standardUserDefaults
        if (code.isEmpty()) {
            defaults.removeObjectForKey("AppleLanguages")
            defaults.removeObjectForKey("AppleLocale")
        } else {
            defaults.setObject(listOf(code), "AppleLanguages")
            defaults.setObject(code, "AppleLocale")
        }
        // Force immediate persistence for debug environments
        defaults.synchronize()
    }
}
