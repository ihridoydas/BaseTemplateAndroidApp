package template.common.di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.*
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import template.common.network.ApiService
import template.common.network.createHttpClient
import template.storage.local.StorageComponent
import template.theme.splashScreen.SplashViewModel

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule)
    }

/**
 * Helper for iOS initialization
 */
fun doInitKoin() = initKoin {}

val networkModule = module {
    single { createHttpClient() }
    singleOf(::ApiService)
}

val storageModule = module {
    single { StorageComponent.createLanguageDataStore() }
    single { StorageComponent.createThemeDataStore() }
}

val viewModelModule = module {
    singleOf(::SplashViewModel)
}

val appModule = module {
    includes(networkModule, storageModule, viewModelModule)
}
