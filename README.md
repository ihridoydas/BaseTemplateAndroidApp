# Base Template Android App

Kickstart your Android app development with this GitHub template repository. Designed by [Hridoy Chandra Das](https://github.com/ihridoydas), it provides essential tools without imposing code-writing opinions.

## Why This Template?

- **Freedom to Choose:** No opinions on code structure or architecture. Developers decide on their own.
- **Opinionated Tooling:** Configured dependency management, git hooks, code formatting, and static analysis for enhanced development.

Inspired by [AndroidAppTemplate](https://github.com/AdamMc331/AndroidAppTemplate).

## Getting Started

1. Click "Use this template" to create a repository under your account.
    ```dsl
   
   templateName             : "template",
   templateAppId            : "template.app.id",
   templateMaterialThemeName: "TemplateTheme",
   newTemplateName          : "Project", [Enter your project name here]
   newTemplateAppId         : "domain.yourname.app", [Enter your project package name here]
   newMaterialThemeName     : "MyMaterialTheme", [Enter your project theme name here]
   useHiltDependencies      : true,
   useRoomDependencies      : true,
   useRetrofitDependencies  : true,
   usePaparazziDependencies : true,
   
   ```
2. Customize by adjusting [setup.gradle](buildscripts/setup.gradle) and running `./gradlew renameAllModules`.

- Japanese [Readme here](https://github.com/ihridoydas/BaseTemplateAndroidApp/blob/develop/README_jp.md) 🇯🇵.
- Bangla [Readme here](https://github.com/ihridoydas/BaseTemplateAndroidApp/blob/develop/README_bd.md) 🇧🇩.
- Hindi [Readme here](https://github.com/ihridoydas/BaseTemplateAndroidApp/blob/develop/README_in.md) 🇮🇳.

## What's Included

Explore third-party dependencies and documentation in [/documentation](/documentation). Notable inclusions:

- [Ktlint](/documentation/StaticAnalysis.md) for code formatting.
- [Detekt](/documentation/StaticAnalysis.md) for code smells.
- [Git Hooks](/documentation/GitHooks.md) for static analysis checks.
- [GitHub Actions](/documentation/GitHubActions.md) for continuous integration.
- [LeakCanary](https://square.github.io/leakcanary/) for detecting memory leaks.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) and [Room](https://developer.android.com/training/data-storage/room) dependencies (removable via setup.gradle).
- [Paparazzi](https://github.com/cashapp/paparazzi) dependency (removable via setup.gradle).
- [Dokka](https://github.com/Kotlin/dokka) dependency, which document all project and module.
- [Spotless](https://github.com/diffplug/spotless) dependency, which is Keep your code spotless.
- [sortDependencies](https://github.com/square/gradle-dependencies-sorter) dependency, which is Sorts dependencies in build.gradle files.

## Dependency Setup

Dependencies are structured in [/buildscripts](/buildscripts). App module dependencies defined using a Gradle version catalog in [libs.versions.toml](gradle/libs.versions.toml).

## Danger Checks

Uses [Danger](https://danger.systems) for PR checks. See [Dangerfile](Dangerfile). Set up a Danger API key in GitHub secrets for GitHub Actions.

## Templates

Includes [Pull Request Template](/.github/pull_request_template.md) for organized PR descriptions.
