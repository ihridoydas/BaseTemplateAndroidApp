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

import android.os.Build
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

/**
 * ルート化されている兆候が見られるか確認するメソッドを提供する.
 */
@Suppress("UtilityClassWithPublicConstructor")
class RootUtil {
    companion object {
        private val Tag = RootUtil::class.java.simpleName
        private const val PATH_TO_WHICH = "/system/xbin/which"
        private const val CMD_SU = "su"
        private val PATHS_SU = arrayOf(
            "/system/app/Superuser.apk",
            "/system/etc/init.d/99SuperSUDaemon",
            "/dev/com.koushikdutta.superuser.daemon/",
            "/system/xbin/daemonsu",
            "/system/xbin/busybox",
            "/system/xbin/su",
            "/system/sd/xbin/su",
            "/system/bin/su",
            "/system/bin/failsafe/su",
            "/su/bin/su",
            "/sbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/data/local/su",
        )

        /**
         * ルート化された端末かどうか確認する.
         */
        fun isDeviceRooted(): Boolean {
            return checkRootBuild() || checkRootApps() || checkRootSu()
        }

        /**
         * テストビルドやカスタム ROM の兆候を確認する.
         */
        private fun checkRootBuild(): Boolean {
            val buildTags = Build.TAGS
            val ret = buildTags != null && buildTags.contains("test-keys")
            if (ret) {
                Log.e(Tag, "Root check is false(Test build or custom build)")
            }
            return ret
        }

        /**
         * ルート化されたデバイスに通常見つかるファイルをチェックする.
         */
        private fun checkRootApps(): Boolean {
            for (path in PATHS_SU) {
                if (File(path).exists()) {
                    Log.e(Tag, "Root check is false(SU application is found)")
                    return true
                }
            }
            return false
        }

        /**
         * su が存在するかどうかを判断する別の方法は、Runtime.getRuntime.exec() で実行を試みる.
         */
        @Suppress("SwallowedException", "TooGenericExceptionCaught")
        private fun checkRootSu(): Boolean {
            var process: Process? = null
            val ret = try {
                process = Runtime.getRuntime().exec(arrayOf(PATH_TO_WHICH, CMD_SU))
                val inputStream = BufferedReader(InputStreamReader(process.inputStream))
                inputStream.readLine() != null
            } catch (t: Throwable) {
                false
            } finally {
                process?.destroy()
            }
            if (ret.not()) {
                Log.e(Tag, "Root check is false(SU is enabled)")
            }
            return ret
        }
    }
}
