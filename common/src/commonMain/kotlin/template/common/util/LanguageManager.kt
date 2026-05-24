package template.common.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import template.storage.local.language.Language
import template.storage.local.language.LanguageDataStore

object LanguageManager {
    private val _currentLanguage = MutableStateFlow(Language.SYSTEM)
    val currentLanguage: StateFlow<Language> = _currentLanguage

    private var dataStore: LanguageDataStore? = null
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun init(languageDataStore: LanguageDataStore) {
        if (dataStore != null) return
        dataStore = languageDataStore
        scope.launch {
            languageDataStore.getLanguage.collect { savedLanguage ->
                println("LanguageManager: Syncing from DataStore -> $savedLanguage")
                _currentLanguage.value = savedLanguage
            }
        }
    }

    fun setLanguage(language: Language) {
        if (_currentLanguage.value == language) return
        println("LanguageManager: Updating language to $language")
        _currentLanguage.value = language
        scope.launch {
            dataStore?.setLanguage(language)
        }
    }
}
