package template.common

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import template.common.di.initKoin

fun main() {
    println("Initializing Koin...")
    try {
        initKoin()
        println("Koin initialized successfully.")
    } catch (e: Exception) {
        println("Koin initialization failed: ${e.message}")
        e.printStackTrace()
    }
    application {
        Window(onCloseRequest = ::exitApplication, title = "Base Template") {
            println("Rendering App...")
            App()
        }
    }
}
