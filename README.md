# Android App Template

This is a GitHub template repository intended to kickstart development on an Android application. This project comes set with a handful of tools that [Hridoy Chandra Das](https://github.com/ihridoydas) finds important and relevant to every project. If you think something is missing, or feel strongly that a setup should be changed, please submit an [Issue](https://github.com/ihridoydas/BaseTemplateAndroidApp/issues/new).

## Why This Template?

The purpose of this template is to avoid any opinions on writing code. The developers should have the freedom to choose their own architecture, third party dependencies, package structure, and more. 

This template _is_ opinionated about developer tooling. Dependency management is configured, git hooks are defined, code formatting and static analysis are all there, and it even has pull request templates. The purpose of this repo is to help you get started building your next project with confidence in your code, and not telling you how to write it. **Inspired by** [AndroidAppTemplate](https://github.com/AdamMc331/AndroidAppTemplate)

## Using This Template

To use this template in your own project, click the "Use this template" button at the top right of the repository. Once you do, a repository will be created for your account that you can clone and use on your device.

To setup this repository to your needs, open the [setup.gradle](buildscripts/setup.gradle) file 
and tweak the `renameConfig` block to your needs. After that, you can run the `renameAllModules` →　`./gradlew renameAllModules`
gradle task to have the app module's package name and relevant strings replaced.

## What's Included

A number of third party dependencies are included in this template. They are also documented inside the [documentation folder](/documentation). The files inside this documentation folder are written in such a way that you can keep them in your real project, to let team members read up on why dependencies are included and how they work.

The dependencies in the template include:

* [Ktlint](/documentation/StaticAnalysis.md) for formatting.
* [Detekt](/documentation/StaticAnalysis.md) for code smells.
* [Git Hooks](/documentation/GitHooks.md) for automatically perform static analysis checks. 
* [Gradle Versions Plugin](/documentation/VersionsPlugin.md) for checking all dependencies for new versions.
* [GitHub Actions](/documentation/GitHubActions.md) for running continuous integration and ensuring code quality with every PR.
* [LeakCanary](https://square.github.io/leakcanary/) for detecting memory leaks.
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) dependencies, which can be removed via setup.gradle if necessary.
* [Room](https://developer.android.com/training/data-storage/room) dependencies, which can be removed via setup.gradle if necessary.
* [Paparazzi](https://github.com/cashapp/paparazzi) dependncy, which can be removed via setup.gradle if necessary.

### Danger

This template uses [Danger](https://danger.systems) which will perform some checks against our 
pull requests. You can find the list of checks in the [Dangerfile](Dangerfile). In addition, we 
have a GitHub Actions workflow for Danger checks. In order for that to work, you'll need a 
Danger API key setup in your GitHub secrets. Info on this can be found [here](https://www.jessesquires.com/blog/2020/04/10/running-danger-on-github-actions/). 

### Templates

There are also templates within this template. This repo comes shipped with a [Pull Request Template](/.github/pull_request_template.md) that will help you and your team write organized and detailed pull request descriptions. 

## Dependency Setup

You may notice that dependencies are set up in a very specific way. Each of the tools has its own Gradle file in the [buildscripts folder](/buildscripts). This is by design so that if you chose to have a multi module project, these dependencies can easily be shared between them. This is already configured inside our root `build.gradle.kts` file, by applying to each sub project:

```groovy
subprojects {
    apply from: "../buildscripts/detekt.gradle"
    apply from: "../buildscripts/versionsplugin.gradle"
}
```

In addition, all of the app module dependencies are defined using a gradle version catalog, found in this [toml](gradle/libs.versions.toml) file.

