plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    id(libs.plugins.dokka.get().pluginId)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "template.navigation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.common)
    // UI
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)

    //Navigation
    //https://developer.android.com/jetpack/androidx/releases/navigation
    implementation(libs.androidx.compose.navigation)


    // Test
    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)
    // Others
    debugImplementation(libs.square.leakcanary)

    annotationProcessor(libs.androidx.room.compiler)

    testImplementation(libs.junit)

    testImplementation(libs.androidx.test.espresso.core)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.compose.ui.test.junit)
    testImplementation(libs.hilt.android.testing)

}
