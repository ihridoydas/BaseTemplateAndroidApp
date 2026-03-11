import com.android.build.api.dsl.LibraryExtension

plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    id(libs.plugins.dokka.get().pluginId)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
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

    //Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

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
