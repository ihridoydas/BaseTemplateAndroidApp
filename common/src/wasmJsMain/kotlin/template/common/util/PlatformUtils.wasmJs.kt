package template.common.util

import kotlinx.browser.window

actual object PlatformUtils {
    actual fun changeLanguage(code: String) {
        // For Web, changing the locale of the running app is tricky.
        // One way is to reload the page with a language parameter, 
        // or if using a library that supports it, update the state.
        // For now, let's at least log it.
        println("Changing language to: $code")
        
        // We can try to set the lang attribute
        kotlinx.browser.document.documentElement?.setAttribute("lang", code)
        
        // Some libraries might pick up window.navigator.language, but we can't change that.
    }
}
