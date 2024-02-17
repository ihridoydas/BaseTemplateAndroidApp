plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.dokka.get().pluginId)
}

android {
    namespace = "template.storage"

    buildFeatures {
        compose = true
    }
}

  dependencies {
      implementation(projects.common)
      implementation(libs.kotlin.coroutines)

  }
