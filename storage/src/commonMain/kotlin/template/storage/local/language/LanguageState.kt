package template.storage.local.language

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class Language {
    @SerialName("unknown")
    UNKNOWN,
    @SerialName("system")
    SYSTEM,
    @SerialName("english")
    ENGLISH,
    @SerialName("japanese")
    JAPANESE,
    @SerialName("bengali")
    BENGALI
}

@Serializable
data class LanguageState(
    val language: Language = Language.UNKNOWN
)
