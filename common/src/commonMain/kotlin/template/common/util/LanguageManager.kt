package template.common.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import template.storage.local.language.Language
import template.storage.local.language.LanguageDataStore

object LanguageManager {
    // Start with UNKNOWN to ensure the first real value (even SYSTEM) is seen as a change
    private val _currentLanguage = MutableStateFlow(Language.UNKNOWN)
    val currentLanguage: StateFlow<Language> = _currentLanguage.asStateFlow()

    private var dataStore: LanguageDataStore? = null
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var isInitialized = false

    fun init(languageDataStore: LanguageDataStore) {
        if (isInitialized) return
        isInitialized = true
        dataStore = languageDataStore
        
        scope.launch {
            languageDataStore.getLanguage.collect { savedLanguage ->
                println("LanguageManager: DataStore emitted -> $savedLanguage")
                
                // If the DataStore has UNKNOWN (new install), we default to SYSTEM
                val finalLanguage = if (savedLanguage == Language.UNKNOWN) Language.SYSTEM else savedLanguage
                
                // Only sync if we haven't manually set something else yet
                if (_currentLanguage.value == Language.UNKNOWN) {
                    println("LanguageManager: Initial sync -> $finalLanguage")
                    _currentLanguage.value = finalLanguage
                }
            }
        }
    }

    fun setLanguage(language: Language) {
        println("LanguageManager: setLanguage -> $language")
        _currentLanguage.value = language
        
        scope.launch {
            println("LanguageManager: Persistence -> Saving $language")
            dataStore?.setLanguage(language)
        }
    }
}
