package template.storage.local.language

import kotlinx.serialization.Serializable

@Serializable
enum class Language {
    SYSTEM,
    ENGLISH,
    JAPANESE,
    BENGALI
}

@Serializable
data class LanguageState(
    val language: Language = Language.ENGLISH
)
