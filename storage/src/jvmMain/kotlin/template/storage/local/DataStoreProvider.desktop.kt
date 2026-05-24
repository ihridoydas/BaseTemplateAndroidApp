package template.storage.local

import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import java.io.File

actual fun producePath(fileName: String): Path {
    return File(System.getProperty("user.home"), fileName).absolutePath.toPath()
}

actual val systemFileSystem: FileSystem = FileSystem.SYSTEM
