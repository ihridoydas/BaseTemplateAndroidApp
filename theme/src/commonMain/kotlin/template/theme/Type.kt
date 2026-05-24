package template.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import template.theme.generated.resources.Res
import template.theme.generated.resources.noto_sans_jp
import template.theme.generated.resources.noto_sans_bengali

@Composable
fun getAppFontFamily() = FontFamily(
    Font(Res.font.noto_sans_jp, FontWeight.Normal),
    Font(Res.font.noto_sans_bengali, FontWeight.Normal)
)

@Composable
fun getTypography(): Typography {
    val fontFamily = getAppFontFamily()
    return Typography(
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp,
        ),
    )
}
