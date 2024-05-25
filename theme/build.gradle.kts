plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.dokka.get().pluginId)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "template.theme"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // UI
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.foundation.android)

}
