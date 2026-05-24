package template.common.util

import kotlinx.browser.window
import kotlinx.browser.localStorage

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        val targetCode = if (code.isEmpty()) "en" else code
        val savedLang = localStorage.getItem("app_lang")
        
        if (savedLang != targetCode) {
            localStorage.setItem("app_lang", targetCode)
            println("PlatformUtils Web: Language changed to $targetCode. Reloading page...")
            window.location.reload()
        }
    }
}
