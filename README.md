# KmpTemplate

Kickstart your **Kotlin Multiplatform (KMP)** development with this KmpTemplate. Designed by [Hridoy Chandra Das](https://github.com/ihridoydas), it provides a robust starting point for Android, iOS, Desktop, and Web (Wasm) applications.

## Why KmpTemplate?

- **Multiplatform by Default:** Shared logic and UI (Compose Multiplatform) for Android, iOS, Desktop, and Web.
- **Opinionated Tooling:** Configured dependency management, git hooks, code formatting, and static analysis for enhanced development.
- **Smart Setup:** A custom script to rename your project, package IDs, and toggle essential KMP libraries in seconds.

## Getting Started

1. Click **"Use this template"** to create a repository under your account.
2. Open `buildscripts/setup.gradle` and configure your project details:
    ```groovy
    def renameConfig = [
        newTemplateName          : "MyAwesomeApp",    // Your project name
        newTemplateAppId         : "com.example.app", // Your package ID
        newMaterialThemeName     : "AppTheme",        // Your Material Theme name

        // Toggle KMP libraries
        useKoin                  : true,
        useKtor                  : true,
        useRoomKmp               : true,
        useComposeMultiplatform  : true,
    ]
    ```
3. Run the setup command in your terminal:
    ```bash
    ./gradlew renameTemplate
    ```
4. **Restart Android Studio**, re-sync Gradle, and you are ready to build!

- Japanese [Readme here](README_jp.md) 🇯🇵.
- Bangla [Readme here](README_bd.md) 🇧🇩.
- Hindi [Readme here](README_in.md) 🇮🇳.

## What's Included

Explore shared logic, components, and documentation:

- [Essential KMP Tasks](/documentation/EssentialTasks.md) - **Start here** for platform-specific commands.
- [Ktlint](/documentation/StaticAnalysis.md) for code formatting.
- [Detekt](/documentation/StaticAnalysis.md) for code smells.
- [Git Hooks](/documentation/GitHooks.md) for pre-commit checks.
- [GitHub Actions](/documentation/GitHubActions.md) for CI/CD.
- [Dokka](/documentation/StaticAnalysis.md) for API documentation.
- [Spotless](https://github.com/diffplug/spotless) & [sortDependencies](https://github.com/square/gradle-dependencies-sorter).

## Project Structure

- `:app`: Android-specific application module.
- `:common`: The heart of your project. Contains shared UI (Compose) and business logic.
- `:navigation`: Shared navigation configuration.
- `:storage`: Shared local data handling (DataStore/Room).
- `:theme`: Shared Material 3 design system.

## CI/CD & Quality

Uses [Danger](https://danger.systems) for PR checks. See [Dangerfile](Dangerfile). Ensure you set up a `DANGER_GITHUB_API_TOKEN` in GitHub Secrets.

## Templates

Includes [Pull Request Template](/.github/pull_request_template.md) for organized PR descriptions.
