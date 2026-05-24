package template.storage.local.theme

import androidx.datastore.core.okio.OkioSerializer
import kotlinx.serialization.json.Json
import okio.BufferedSink
import okio.BufferedSource
import kotlinx.serialization.encodeToString

object ThemeSerializer : OkioSerializer<ThemeState> {
    override val defaultValue: ThemeState = ThemeState()

    override suspend fun readFrom(source: BufferedSource): ThemeState {
        return try {
            Json.decodeFromString<ThemeState>(source.readUtf8())
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: ThemeState, sink: BufferedSink) {
        sink.writeUtf8(Json.encodeToString(t))
    }
}
