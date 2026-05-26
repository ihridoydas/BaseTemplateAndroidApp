import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.detektGradlePlugin)
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.sortDependencies) apply false
    alias(libs.plugins.kotlinter) apply false
    alias(libs.plugins.dokka) apply false
    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.gradle.versions) apply false
    alias(libs.plugins.spotless) apply false
}

apply(from = "buildscripts/githooks.gradle")
apply(from = "buildscripts/setup.gradle")
apply(from = "buildscripts/versionsplugin.gradle")

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    
    extensions.configure<org.jetbrains.dokka.gradle.DokkaExtension>("dokka") {
        dokkaPublications.configureEach {
            outputDirectory.set(layout.buildDirectory.dir("docs/${project.name}"))
        }
        dokkaSourceSets.configureEach {
            enableAndroidDocumentationLink.set(true)
        }
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = "17"
        config.setFrom(rootProject.layout.projectDirectory.file("config/detekt/detekt.yml"))
    }

    plugins.withType<AppPlugin> {
    }

    plugins.withType<LibraryPlugin> {
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

tasks.register<io.gitlab.arturbosch.detekt.Detekt>("detektAll") {
    parallel = true
    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
    config.setFrom(project.layout.projectDirectory.file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = false
}
