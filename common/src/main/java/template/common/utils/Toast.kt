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
package template.common.utils

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(
    message: String,
    onToastDisplayChange: (Boolean) -> Unit,
) {
    showToast(message, onToastDisplayChange)
}

fun Context.toast(
    @StringRes message: Int,
    onToastDisplayChange: (Boolean) -> Unit,
) {
    showToast(getString(message), onToastDisplayChange)
}

private fun Context.showToast(
    message: String,
    onToastDisplayChange: (Boolean) -> Unit,
) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).also {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            it.addCallback(object : Toast.Callback() {
                override fun onToastHidden() {
                    super.onToastHidden()
                    onToastDisplayChange(false)
                }

                override fun onToastShown() {
                    super.onToastShown()
                    onToastDisplayChange(true)
                }
            })
        }
    }.show()
}
