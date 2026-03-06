import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(libs.plugins.detektGradlePlugin.get().pluginId).version(libs.versions.detektGradlePlugin)
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    id(libs.plugins.sortDependencies.get().pluginId).version(libs.versions.sortDependencies).apply(false)
    alias(libs.plugins.kotlinter) apply false
    id(libs.plugins.dokka.get().pluginId).version(libs.versions.dokkaVersion).apply(false)
    alias(libs.plugins.compose.compiler) apply false
}

buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
        maven {
            setUrl("https://jitpack.io")
        }

    }

    dependencies {
        classpath(libs.detekt.gradle.plugin)
        classpath(libs.gradle)
        classpath(libs.gradle.versions.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.plugin)
        classpath(libs.spotless)
        classpath(libs.protobuf.gradle.plugin)
        classpath(libs.dokkaDocumentation.get())
        classpath(libs.dokka.gradle.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}


apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/setup.gradle")
apply(from = "buildscripts/versionsplugin.gradle")

subprojects {
    apply(plugin ="org.jetbrains.dokka")
    
    // Dokka V2 configuration
    extensions.configure<org.jetbrains.dokka.gradle.DokkaExtension>("dokka") {
        dokkaPublications.configureEach {
            outputDirectory.set(rootProject.layout.buildDirectory.dir("docs/${project.name}"))
        }
        dokkaSourceSets.configureEach {
            enableAndroidDocumentationLink.set(true)
        }
    }
}

val clean by tasks.registering(Delete::class) {
    delete(rootProject.layout.buildDirectory.get())
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
        dependsOn(":installGitHooks")
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "17"
    config.setFrom(layout.projectDirectory.file("config/detekt/detekt.yml"))
}

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("detektAll") {
    parallel = true
    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
    config.setFrom(layout.projectDirectory.file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = false
}

subprojects {
    plugins.whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                extensions.configure<ApplicationExtension>("android") {
                    compileSdk = libs.versions.compileSdk.get().toInt()
                    namespace = "template"

                    defaultConfig {
                        minSdk = libs.versions.minSdk.get().toInt()
                        targetSdk = libs.versions.compileSdk.get().toInt()
                        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                        vectorDrawables {
                            useSupportLibrary = true
                        }
                    }
                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }

                    packaging {
                        resources {
                            excludes += "/META-INF/{AL2.0,LGPL2.1}"
                        }
                    }

                    buildFeatures {
                        compose = true
                    }
                }
            }

            is LibraryPlugin -> {
                extensions.configure<LibraryExtension>("android") {
                    compileSdk = libs.versions.compileSdk.get().toInt()
                    namespace = "template.${project.name}"

                    defaultConfig {
                        minSdk = libs.versions.minSdk.get().toInt()
                        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                        consumerProguardFiles("consumer-rules.pro")
                        vectorDrawables {
                            useSupportLibrary = true
                        }
                    }
                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }

                    packaging {
                        resources {
                            excludes += "/META-INF/{AL2.0,LGPL2.1}"
                        }
                    }

                    buildFeatures {
                        compose = true
                    }
                }
            }

            is JavaPlugin -> {
                extensions.configure<JavaPluginExtension> {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }
        }
    }

    afterEvaluate {
        project.apply("${project.rootDir}/spotless.gradle")
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.addAll(
                listOf("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
            )
        }
    }
}
