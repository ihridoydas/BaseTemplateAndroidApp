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
package template.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import template.storage.local.language.Language

import org.koin.compose.koinInject

@Composable
fun LanguageDropdown() {
    val currentLanguage by template.common.util.LanguageManager.currentLanguage.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center,
    ) {
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier.align(Alignment.Center),
        ) {
            Icon(
                imageVector = Icons.Default.Language,
                contentDescription = "Language Menu",
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            // System Default
            DropdownMenuItem(
                text = {
                    Row {
                        Text("System Default")
                        if (currentLanguage == Language.SYSTEM) {
                            Spacer(Modifier.width(5.dp))
                            Text("✓")
                        }
                    }
                },
                onClick = {
                    expanded = false
                    template.common.util.LanguageManager.setLanguage(Language.SYSTEM)
                },
            )
            // English
            DropdownMenuItem(
                text = {
                    Row {
                        Text("English")
                        if (currentLanguage == Language.ENGLISH) {
                            Spacer(Modifier.width(5.dp))
                            Text("✓")
                        }
                    }
                },
                onClick = {
                    expanded = false
                    template.common.util.LanguageManager.setLanguage(Language.ENGLISH)
                },
            )
            // Japanese
            DropdownMenuItem(
                text = {
                    Row {
                        Text("日本語")
                        if (currentLanguage == Language.JAPANESE) {
                            Spacer(Modifier.width(5.dp))
                            Text("✓")
                        }
                    }
                },
                onClick = {
                    expanded = false
                    template.common.util.LanguageManager.setLanguage(Language.JAPANESE)
                },
            )
            // Bangla
            DropdownMenuItem(
                text = {
                    Row {
                        Text(if(currentLanguage == Language.BENGALI) "বাংলা" else "Bangla")
                        if (currentLanguage == Language.BENGALI) {
                            Spacer(Modifier.width(5.dp))
                            Text("✓")
                        }
                    }
                },
                onClick = {
                    expanded = false
                    template.common.util.LanguageManager.setLanguage(Language.BENGALI)
                },
            )
        }
    }
}
