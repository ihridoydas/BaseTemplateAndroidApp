import com.android.build.api.dsl.ApplicationExtension
import java.util.Properties

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.ksp.get().pluginId)
    id(libs.plugins.kotlinter.get().pluginId)
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.hilt) apply false
    id(libs.plugins.sortDependencies.get().pluginId)
    id(libs.plugins.dokka.get().pluginId)
    id(libs.plugins.protobuf.get().pluginId)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

extensions.configure<ApplicationExtension>("android") {
    defaultConfig {
        applicationId = "template.app.id"
        versionCode = 1
        versionName = "1.0"
    }

    /*
    //Set in your local.properties file for signing Configs
    //Path Location your keystore
    STORE_FILE = /Users/~/app/jks/template.keystore
    KEY_ALIAS = app_alias
    STORE_PASSWORD = template
    KEY_PASSWORD = template
    */
    /*signingConfigs {
        create("develop") {
            val keystoreProperties = Properties().apply {
                val propFile = rootProject.file("local.properties")
                if (propFile.exists()) {
                    load(propFile.reader())
                }
            }
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS")
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD")
            storeFile = keystoreProperties.getProperty("STORE_FILE")?.let { file(it) }
            storePassword = keystoreProperties.getProperty("STORE_PASSWORD")
        }
        create("staging") {
            val keystoreProperties = Properties().apply {
                val propFile = rootProject.file("local.properties")
                if (propFile.exists()) {
                    load(propFile.reader())
                }
            }
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS")
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD")
            storeFile = keystoreProperties.getProperty("STORE_FILE")?.let { file(it) }
            storePassword = keystoreProperties.getProperty("STORE_PASSWORD")
        }
        create("production") {
            val keystoreProperties = Properties().apply {
                val propFile = rootProject.file("local.properties")
                if (propFile.exists()) {
                    load(propFile.reader())
                }
            }
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS")
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD")
            storeFile = keystoreProperties.getProperty("STORE_FILE")?.let { file(it) }
            storePassword = keystoreProperties.getProperty("STORE_PASSWORD")
        }
    }*/

    // Specifies one flavor dimension.
    flavorDimensions += "version"
    productFlavors {
        create("develop") {
            dimension = "version"
            applicationIdSuffix = ".develop"
            versionNameSuffix = "-develop"
           // signingConfig = signingConfigs.getByName("develop")
        }
        create("staging") {
            dimension = "version"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
           // signingConfig = signingConfigs.getByName("staging")
        }
        create("production") {
            dimension = "version"
            //signingConfig = signingConfigs.getByName("production")
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

    buildFeatures {
        buildConfig = true
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
    //Module
    implementation(projects.common)
    implementation(projects.navigation)
    implementation(projects.storage)
    implementation(projects.theme)
    // UI
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.hilt.compose.navigation)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.android)
    // Navigation 3
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    // Network and Local
    implementation(libs.androidx.room.runtime)
    implementation(libs.bundles.androidx.xr)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    // Storage
    implementation(libs.datastore)
    implementation(libs.hilt.android)
    implementation(libs.ktor.client.core)
    implementation(libs.protobuf.javaLite)
    implementation(libs.protobuf.kotlinLite)
    implementation(libs.square.moshi.kotlin)
    implementation(libs.square.retrofit)
    implementation(libs.square.retrofit.converter.moshi)
    implementation(libs.timber)

    debugImplementation(libs.androidx.ui.test.junit4)
    // Test
    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)
    // Others
    debugImplementation(libs.square.leakcanary)

    compileOnly(libs.androidx.xr.extensions)

    annotationProcessor(libs.androidx.room.compiler)
    // Hilt
    annotationProcessor(libs.hilt.compiler)

    testImplementation(libs.androidx.test.espresso.core)
    testImplementation(libs.androidx.test.junit)
    testImplementation(libs.androidx.ui.test.junit4)
    testImplementation(libs.compose.ui.test.junit)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.junit)

    ksp(libs.androidx.room.compiler)
    ksp(libs.square.moshi.kotlin.codegen)

    kspAndroidTest(libs.hilt.android.compiler)
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
