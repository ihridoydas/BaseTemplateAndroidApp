package template.storage.local

import androidx.datastore.core.DataStore
import androidx.datastore.core.okio.OkioSerializer
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioStorage
import okio.FileSystem
import okio.Path
fun <T : Any> createDataStore(
    producePath: () -> Path,
    serializer: OkioSerializer<T>,
): DataStore<T> = DataStoreFactory.create(
    storage = OkioStorage(
        fileSystem = systemFileSystem,
        serializer = serializer,
        producePath = producePath
    )
)

internal const val DATASTORE_FILE_NAME_LANGUAGE = "language.json"
internal const val DATASTORE_FILE_NAME_THEME = "theme.json"

expect fun producePath(fileName: String): Path

expect val systemFileSystem: FileSystem
