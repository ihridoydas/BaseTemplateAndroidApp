@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
}

android {
    namespace = "template.theme"

    buildFeatures {
        compose = true
    }
}

  dependencies {
      implementation(libs.datastore)
      implementation(libs.protobuf.javaLite)
      implementation(libs.protobuf.kotlinLite)

  }
