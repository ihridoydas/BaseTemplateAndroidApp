package template.storage.local

import android.content.Context
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath

@Volatile
private var _dataStoreContext: Context? = null

var dataStoreContext: Context
    get() = _dataStoreContext ?: throw IllegalStateException("dataStoreContext not initialized")
    set(value) {
        _dataStoreContext = value
    }

actual fun producePath(fileName: String): Path {
    return dataStoreContext.filesDir.resolve(fileName).absolutePath.toPath()
}

actual val systemFileSystem: FileSystem = FileSystem.SYSTEM
