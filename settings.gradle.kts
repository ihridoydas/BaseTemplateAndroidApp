plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "template"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":theme")
include(":navigation")
include(":storage")
include(":common")
