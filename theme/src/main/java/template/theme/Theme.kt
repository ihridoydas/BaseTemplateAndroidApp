/*
* MIT License
*
* Copyright (c) 2024 Hridoy Chandra Das
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package template.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = template_theme_light_primary,
    onPrimary = template_theme_light_onPrimary,
    primaryContainer = template_theme_light_primaryContainer,
    onPrimaryContainer = template_theme_light_onPrimaryContainer,
    secondary = template_theme_light_secondary,
    onSecondary = template_theme_light_onSecondary,
    secondaryContainer = template_theme_light_secondaryContainer,
    onSecondaryContainer = template_theme_light_onSecondaryContainer,
    tertiary = template_theme_light_tertiary,
    onTertiary = template_theme_light_onTertiary,
    tertiaryContainer = template_theme_light_tertiaryContainer,
    onTertiaryContainer = template_theme_light_onTertiaryContainer,
    error = template_theme_light_error,
    errorContainer = template_theme_light_errorContainer,
    onError = template_theme_light_onError,
    onErrorContainer = template_theme_light_onErrorContainer,
    background = template_theme_light_background,
    onBackground = template_theme_light_onBackground,
    surface = template_theme_light_surface,
    onSurface = template_theme_light_onSurface,
    surfaceVariant = template_theme_light_surfaceVariant,
    onSurfaceVariant = template_theme_light_onSurfaceVariant,
    outline = template_theme_light_outline,
    inverseOnSurface = template_theme_light_inverseOnSurface,
    inverseSurface = template_theme_light_inverseSurface,
    inversePrimary = template_theme_light_inversePrimary,
    surfaceTint = template_theme_light_surfaceTint,
    outlineVariant = template_theme_light_outlineVariant,
    scrim = template_theme_light_scrim,
)

private val DarkColors = darkColorScheme(
    primary = template_theme_dark_primary,
    onPrimary = template_theme_dark_onPrimary,
    primaryContainer = template_theme_dark_primaryContainer,
    onPrimaryContainer = template_theme_dark_onPrimaryContainer,
    secondary = template_theme_dark_secondary,
    onSecondary = template_theme_dark_onSecondary,
    secondaryContainer = template_theme_dark_secondaryContainer,
    onSecondaryContainer = template_theme_dark_onSecondaryContainer,
    tertiary = template_theme_dark_tertiary,
    onTertiary = template_theme_dark_onTertiary,
    tertiaryContainer = template_theme_dark_tertiaryContainer,
    onTertiaryContainer = template_theme_dark_onTertiaryContainer,
    error = template_theme_dark_error,
    errorContainer = template_theme_dark_errorContainer,
    onError = template_theme_dark_onError,
    onErrorContainer = template_theme_dark_onErrorContainer,
    background = template_theme_dark_background,
    onBackground = template_theme_dark_onBackground,
    surface = template_theme_dark_surface,
    onSurface = template_theme_dark_onSurface,
    surfaceVariant = template_theme_dark_surfaceVariant,
    onSurfaceVariant = template_theme_dark_onSurfaceVariant,
    outline = template_theme_dark_outline,
    inverseOnSurface = template_theme_dark_inverseOnSurface,
    inverseSurface = template_theme_dark_inverseSurface,
    inversePrimary = template_theme_dark_inversePrimary,
    surfaceTint = template_theme_dark_surfaceTint,
    outlineVariant = template_theme_dark_outlineVariant,
    scrim = template_theme_dark_scrim,
)

@Composable
fun TemplateTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content:
        @Composable()
        () -> Unit,
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
