package template.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalProvidableLocaleList
import org.jetbrains.compose.resources.Font
import template.theme.generated.resources.Res
import template.theme.generated.resources.noto_sans_jp
import template.theme.generated.resources.noto_sans_bengali

@Composable
fun getAppFontFamily(): FontFamily {
    val localeList = LocalProvidableLocaleList.current
    val languageCode = localeList.firstOrNull()?.language ?: ""
    println("Typography: current language for font selection is '$languageCode'")
    
    val jpFont = Font(Res.font.noto_sans_jp, FontWeight.Normal)
    val bengaliFont = Font(Res.font.noto_sans_bengali, FontWeight.Normal)
    
    // On some platforms, especially Web, prioritizing the specific language font
    // helps avoid glyph fallback issues where a previous font might have empty glyphs.
    return when (languageCode) {
        "bn" -> FontFamily(bengaliFont, jpFont)
        "ja" -> FontFamily(jpFont, bengaliFont)
        else -> FontFamily(jpFont, bengaliFont)
    }
}

@Composable
fun getTypography(): Typography {
    val fontFamily = getAppFontFamily()
    val defaultTypography = Typography()
    
    return Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = fontFamily),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = fontFamily),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = fontFamily),
        
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = fontFamily),
        
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = fontFamily),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = fontFamily),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = fontFamily),
        
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = fontFamily),
        
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = fontFamily),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = fontFamily),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = fontFamily),
    )
}
