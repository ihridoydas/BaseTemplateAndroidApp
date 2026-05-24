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

// Set of Material typography styles to start with
@Composable
fun getTypography() = Typography(
    bodyMedium = TextStyle(
        fontFamily = getAppFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = getAppFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = getAppFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
    ),
)

