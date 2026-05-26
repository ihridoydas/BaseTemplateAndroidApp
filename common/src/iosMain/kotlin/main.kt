package template.common

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    App(
        onLanguageChange = { code ->
            template.common.util.PlatformUtils.changeLanguage(code)
        },
        onThemeChange = { isDark ->
            template.common.util.PlatformUtils.changeTheme(isDark)
        }
    )
}
