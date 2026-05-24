package template.storage.local.language

import androidx.datastore.core.okio.OkioSerializer
import kotlinx.serialization.json.Json
import okio.BufferedSink
import okio.BufferedSource
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

object LanguageSerializer : OkioSerializer<LanguageState> {
    override val defaultValue: LanguageState = LanguageState()

    override suspend fun readFrom(source: BufferedSource): LanguageState {
        return try {
            Json.decodeFromString<LanguageState>(source.readUtf8())
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: LanguageState, sink: BufferedSink) {
        sink.writeUtf8(Json.encodeToString(t))
    }
}
