# Static Analysis

This project leverages static analysis to ensure that the codebase meets certain standards that can be verified through automation. Two of these libraries are Detekt and Ktlint.

## Detekt

[Detekt](https://github.com/detekt/detekt) is a static analysis tool that checks for code smells. Examples include magic numbers, complicated conditionals, long methods, long parameter lists, and so much more. It is highly configurable, and if you choose to turn off any checks or customize thresholds you can do so in the [config file](/config/detekt/detekt.yml).

To run a detekt validation, use the one of the following Gradle commands:

```bash
./gradlew detekt # Runs over each module synchronously
./gradlew detektAll # Runs over the entire project in parallel (Recommended).
```

## Ktlint

[Ktlint](https://github.com/pinterest/ktlint) is a static analysis tool from Pinterest that prevents bike shedding when it comes to code formatting. It also comes with a Gradle task to automatically format your entire codebase, if it can.

We use the [Kotlinter](https://github.com/jeremymailen/kotlinter-gradle) Ktlint Gradle plugin in this project.

The following Gradle commands can be helpful:

```bash
./gradlew formatKotlin # Will format the codebase
./gradlew lintKotlin # Will check if everything is formatted correctly
```

## Other Quality Tasks

```bash
./gradlew sortDependencies # Sorts dependencies in build.gradle files
./gradlew spotlessApply # Applies spotless formatting
./gradlew spotlessCheck # Checks spotless formatting
./gradlew check # Runs all static analysis and tests
```
