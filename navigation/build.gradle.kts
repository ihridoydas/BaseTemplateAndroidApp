plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    id(libs.plugins.dokka.get().pluginId)
}

android {
    namespace = "template.navigation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.common)
    // Gradle
    implementation(platform(libs.compose.bom))
    // UI
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)

    //Navigation
    //https://developer.android.com/jetpack/androidx/releases/navigation
    implementation(libs.androidx.compose.navigation)


    // Test
    debugImplementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)
    // Others
    debugImplementation(libs.square.leakcanary)

    annotationProcessor(libs.androidx.room.compiler)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.compose.ui.test.junit)
    androidTestImplementation(libs.hilt.android.testing)

}
