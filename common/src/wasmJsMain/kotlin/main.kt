package template.common

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import template.common.di.initKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    println("Initializing Koin for Web...")
    try {
        initKoin()
        println("Koin initialized successfully for Web.")
    } catch (e: Exception) {
        println("Koin initialization failed: ${e.message}")
    }
    ComposeViewport(viewportContainerId = "ComposeTarget") {
        App(onLanguageChange = { code ->
            template.common.util.PlatformUtils.changeLanguage(code)
        })
    }
}
