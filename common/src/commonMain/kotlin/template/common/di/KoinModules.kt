package template.common.di

import org.koin.dsl.module
import template.common.network.ApiService
import template.common.network.createHttpClient
import template.storage.local.StorageComponent
import template.storage.local.language.LanguageDataStore
import template.storage.local.theme.ThemeLocalDataStore
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import template.theme.splashScreen.SplashViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule)
    }

/**
 * Helper for iOS initialization
 */
fun initKoin() = initKoin {}

val networkModule = module {
    single { createHttpClient() }
    singleOf(::ApiService)
}

val storageModule = module {
    single { StorageComponent.createLanguageDataStore() }
    single { StorageComponent.createThemeDataStore() }
}

val viewModelModule = module {
    viewModelOf(::SplashViewModel)
}

val appModule = module {
    includes(networkModule, storageModule, viewModelModule)
}
