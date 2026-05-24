package template.storage.local

import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import okio.fakefilesystem.FakeFileSystem

actual fun producePath(fileName: String): Path {
    return "/$fileName".toPath()
}

actual val systemFileSystem: FileSystem = FakeFileSystem()
