import com.android.build.api.dsl.ApplicationExtension
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.sortDependencies)
    alias(libs.plugins.dokka)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "template"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "template.app.id"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create("develop") {
            dimension = "version"
            applicationIdSuffix = ".develop"
            versionNameSuffix = "-develop"
        }
        create("staging") {
            dimension = "version"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
        }
        create("production") {
            dimension = "version"
        }
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
            buildConfigField("String", "Template_HOST", "\"192.168.10.34\"")
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "Template_HOST", "\"not given\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    sourceSets {
        getByName("main") {
            java.setSrcDirs(listOf("src/main/java", "src/main/kotlin"))
        }
    }
}

androidComponents {
    onVariants { variant ->
        val flavorName = variant.flavorName ?: ""
        val buildType = variant.buildType ?: ""

        val appLabel = when (buildType) {
            "debug" -> when (flavorName) {
                "develop" -> "${rootProject.name} devDebug"
                "staging" -> "${rootProject.name} stgDebug"
                "production" -> "${rootProject.name} proDebug"
                else -> "${rootProject.name} devDebug"
            }
            else -> when (flavorName) {
                "develop" -> "${rootProject.name} Develop"
                "staging" -> "${rootProject.name} Staging"
                "production" -> rootProject.name
                else -> rootProject.name
            }
        }
        variant.manifestPlaceholders.put("appLabel", appLabel)
    }
}

dependencies {
    // UI
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    
    // Use Compose Multiplatform dependencies
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material3)
    implementation(compose.ui)
    implementation(compose.components.resources)

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    // Network and Local
    implementation(libs.androidx.room.runtime)
    implementation(libs.bundles.androidx.xr)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    // Storage
    implementation(libs.datastore.android)
    implementation(libs.timber)
    //Module
    implementation(projects.common)
    implementation(projects.navigation)
    implementation(projects.storage)
    implementation(projects.theme)

    debugImplementation(compose.uiTooling)
    // Others
    debugImplementation(libs.square.leakcanary)

    annotationProcessor(libs.androidx.room.compiler)

    testImplementation(libs.androidx.test.espresso.core)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.compose.ui.test.junit)
    testImplementation(libs.junit)

    ksp(libs.androidx.room.compiler)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("kotlin") {
                    option("lite")
                }
            }
            task.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}
