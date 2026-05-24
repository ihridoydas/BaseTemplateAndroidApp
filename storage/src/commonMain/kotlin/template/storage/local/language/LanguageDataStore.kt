package template.storage.local.language

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LanguageDataStore(
    private val dataStore: DataStore<LanguageState>,
) {
    val getLanguage: Flow<Language> =
        dataStore.data.map { it.language }

    suspend fun setLanguage(language: Language) {
        dataStore.updateData { current ->
            current.copy(language = language)
        }
    }
}
