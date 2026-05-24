package template.storage.local.theme

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeLocalDataStore(
    private val dataStore: DataStore<ThemeState>,
) {
    val themeMode: Flow<ThemeMode> =
        dataStore.data.map { it.themeMode }

    suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.updateData { current ->
            current.copy(themeMode = mode)
        }
    }
}
