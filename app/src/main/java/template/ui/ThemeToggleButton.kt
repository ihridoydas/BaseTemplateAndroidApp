/*
* MIT License
*
* Copyright (c) 2026 Hridoy Chandra Das
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
package template.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import template.datastore.ThemePreferences

@Composable
fun ThemeToggleButton(
    themeMode: ThemePreferences.ThemeMode,
    onToggle: (ThemePreferences.ThemeMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        modifier = modifier,
        onClick = {
            val newMode = when (themeMode) {
                ThemePreferences.ThemeMode.LIGHT -> {
                    ThemePreferences.ThemeMode.DARK
                }

                ThemePreferences.ThemeMode.DARK -> {
                    ThemePreferences.ThemeMode.LIGHT
                }

                ThemePreferences.ThemeMode.SYSTEM -> {
                    ThemePreferences.ThemeMode.DARK
                }

                else -> {
                    ThemePreferences.ThemeMode.DARK
                }
            }

            onToggle(newMode)
        },
    ) {
        Icon(
            imageVector =
                if (themeMode == ThemePreferences.ThemeMode.DARK) {
                    Icons.Default.DarkMode
                } else {
                    Icons.Default.LightMode
                },
            contentDescription = "Toggle Theme",
        )
    }
}
