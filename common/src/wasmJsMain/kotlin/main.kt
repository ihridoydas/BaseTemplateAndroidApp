package template.common

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import template.common.di.initKoin
import template.common.util.PlatformUtils
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    println("Web App: main() starting...")
    try {
        println("Web App: Initializing Koin...")
        initKoin()
        println("Web App: Koin initialized.")
    } catch (e: Exception) {
        println("Web App: Koin initialization failed: ${e.message}")
    }

    val canvas = document.getElementById("ComposeTarget")
    if (canvas == null) {
        println("Web App ERROR: 'ComposeTarget' element not found in index.html!")
    } else {
        println("Web App: Found 'ComposeTarget' element.")
    }

    ComposeViewport(viewportContainerId = "ComposeTarget") {
        println("Web App: Rendering App...")
        App(
            onLanguageChange = { code ->
                PlatformUtils.changeLanguage(code)
            },
            onThemeChange = { isDark ->
                PlatformUtils.changeTheme(isDark)
            }
        )
    }
}
