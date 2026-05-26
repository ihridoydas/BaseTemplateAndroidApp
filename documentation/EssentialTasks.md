# Essential KMP Tasks

This project is a Kotlin Multiplatform (KMP) project supporting Android, iOS, Desktop (JVM), and Web (Wasm). Below are the essential Gradle tasks for development.

## Project Structure
- `:app`: Android application module.
- `:common`: Shared code (UI, Logic) for all platforms.
- `:navigation`: Shared navigation logic.
- `:storage`: Shared data storage logic (DataStore, Database).
- `:theme`: Shared theme and UI components.

## Android Tasks
- **Build APK**: `./gradlew :app:assembleDebug`
- **Run Unit Tests**: `./gradlew :app:testDebugUnitTest`
- **Install & Run**: `./gradlew :app:installDebug`

## iOS Tasks
- **Build Binaries**: `./gradlew iosArm64MainBinaries` (for physical device)
- **Build for Simulator**: `./gradlew iosSimulatorArm64MainBinaries`
- **Link Framework**: `./gradlew :common:linkReleaseFrameworkIosArm64`

## Desktop (JVM) Tasks
- **Run Desktop App**: `./gradlew :common:run`
- **Build Desktop Jar**: `./gradlew :common:desktopJar`

## Web (Wasm-JS) Tasks
- **Run Web App**: `./gradlew :common:wasmJsBrowserDevelopmentRun`
- **Build Web Distribution**: `./gradlew :common:wasmJsBrowserDistribution`

## Testing (Shared)
- **Run All Tests**: `./gradlew allTests`
- **Run Shared Common Tests**: `./gradlew :common:allTests`

## Code Quality & Documentation
- **Run Static Analysis (Detekt)**: `./gradlew detektAll`
- **Run Code Formatting (Ktlint)**: `./gradlew ktlintCheck`
- **Apply Code Formatting**: `./gradlew ktlintFormat`
- **Generate Documentation (Dokka)**: `./gradlew dokkaHtml`
- **Sort Dependencies**: `./gradlew sortDependencies`

## Git Hooks
- **Install Hooks**: `./gradlew installGitHooks`
- **Copy Hooks**: `./gradlew copyGitHooks`
