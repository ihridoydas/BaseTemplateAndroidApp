import com.android.build.api.dsl.LibraryExtension

plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.dokka.get().pluginId)
    alias(libs.plugins.compose.compiler)
}


dependencies {
    implementation(projects.common)
    implementation(libs.kotlin.coroutines)
    testImplementation(libs.androidx.ui.test.junit4)
}
