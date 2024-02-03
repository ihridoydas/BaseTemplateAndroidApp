import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTask
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
        classpath (libs.protobuf.gradle.plugin)
        classpath (libs.dokkaDocumentation.get())
        classpath (libs.dokka.gradle.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }

    // Currently overriding the ktlint version to 1.0.1, can remove in the future when kotlinter
    // is updated: https://github.com/jeremymailen/kotlinter-gradle#custom-ktlint-version
    configurations.classpath {
        resolutionStrategy {
            force(
                "com.pinterest.ktlint:ktlint-rule-engine:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-rule-engine-core:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-core:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-checkstyle:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-json:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-html:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-plain:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-cli-reporter-sarif:${libs.versions.ktlint.get()}",
                "com.pinterest.ktlint:ktlint-ruleset-standard:${libs.versions.ktlint.get()}",
            )
        }
    }
}


apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/setup.gradle")
apply(from = "buildscripts/versionsplugin.gradle")

subprojects {
    apply(from = "../buildscripts/detekt.gradle")
    apply(plugin ="org.jetbrains.dokka")
    // configure all format tasks at once
    tasks.withType<DokkaTask>().configureEach {
        dokkaSourceSets.configureEach {
            outputDirectory.set(rootProject.mkdir("docs/"))
            noAndroidSdkLink.set(false)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

afterEvaluate {
    // We install the hook at the first occasion
    tasks.named("clean") {
        dependsOn(":installGitHooks")
    }
}

tasks {
    /**
     * The detektAll tasks enables parallel usage for detekt so if this project
     * expands to multi module support, detekt can continue to run quickly.
     *
     * https://proandroiddev.com/how-to-use-detekt-in-a-multi-module-android-project-6781937fbef2
     */
    @Suppress("UnusedPrivateMember")
    val detektAll by registering(io.gitlab.arturbosch.detekt.Detekt::class) {
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
        buildUponDefaultConfig = false
    }
}

fun BaseExtension.defaultConfig() {

    compileSdkVersion(libs.versions.compileSdk.get().toInt())

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.compileSdk.get().toInt()
        consumerProguardFiles("consumer-rules.pro")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    @Suppress("UnstableApiUsage")
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun PluginContainer.applyDefaultConfig(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> {
                project.extensions
                    .getByType<AppExtension>()
                    .apply {
                        defaultConfig()
                    }
            }

            is LibraryPlugin -> {
                project.extensions
                    .getByType<LibraryExtension>()
                    .apply {
                        defaultConfig()
                    }
            }

            is JavaPlugin -> {
                project.extensions.getByType<JavaPluginExtension>()
                    .apply {
                        sourceCompatibility = JavaVersion.VERSION_17
                        targetCompatibility = JavaVersion.VERSION_17
                    }
            }
        }
    }
}

subprojects {
    project.plugins.applyDefaultConfig(project)
    afterEvaluate {
        project.apply("${project.rootDir}/spotless.gradle")
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.addAll(
                listOf(
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
                )
            )
        }
    }
}
