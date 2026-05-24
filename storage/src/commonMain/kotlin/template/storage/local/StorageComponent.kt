package template.storage.local

import template.storage.local.language.LanguageDataStore
import template.storage.local.language.LanguageSerializer
import template.storage.local.theme.ThemeLocalDataStore
import template.storage.local.theme.ThemeSerializer

object StorageComponent {
    fun createLanguageDataStore(): LanguageDataStore {
        return LanguageDataStore(
            createDataStore(
                producePath = { producePath(DATASTORE_FILE_NAME_LANGUAGE) },
                serializer = LanguageSerializer
            )
        )
    }

    fun createThemeDataStore(): ThemeLocalDataStore {
        return ThemeLocalDataStore(
            createDataStore(
                producePath = { producePath(DATASTORE_FILE_NAME_THEME) },
                serializer = ThemeSerializer
            )
        )
    }
}
