# ベーステンプレート Android アプリ

このGitHubテンプレートリポジトリを使用して、Androidアプリの開発をスタートしましょう。[Hridoy Chandra Das](https://github.com/ihridoydas)によって設計され、コードの記述には意見を押し付けずに、必要なツールを提供します。

## なぜこのテンプレート？

- **選択の自由:** コード構造やアーキテクチャに対する意見はありません。開発者が自分で決めます。
- **意見を述べるツール:** 設定済みの依存関係管理、gitフック、コードフォーマッティング、静的解析など、開発を向上させるためのツールが含まれています。

[AndroidAppTemplate](https://github.com/AdamMc331/AndroidAppTemplate)からのインスピレーション。

## スタートガイド

1. "このテンプレートを使用"をクリックして、アカウントにリポジトリを作成します。
    ```dsl
    templateName             : "template",
    templateAppId            : "template.app.id",
    templateMaterialThemeName: "TemplateTheme",
    newTemplateName          : "Project", [ここにプロジェクト名を入力]
    newTemplateAppId         : "domain.yourname.app", [ここにプロジェクトのパッケージ名を入力]
    newMaterialThemeName     : "MyMaterialTheme", [ここにプロジェクトのテーマ名を入力]
    useHiltDependencies      : true,
    useRoomDependencies      : true,
    useRetrofitDependencies  : true,
    usePaparazziDependencies : true,
    ```
2. [setup.gradle](buildscripts/setup.gradle)を調整し、`./gradlew renameAllModules`を実行してカスタマイズします。

## 含まれるもの

[/documentation](/documentation)のサードパーティーの依存関係とドキュメンテーションを見てください。注目すべきものは以下です：

- コードフォーマット用の[Ktlint](/documentation/StaticAnalysis.md)。
- コードスメル検出のための[Detekt](/documentation/StaticAnalysis.md)。
- 静的解析チェックのための[Git Hooks](/documentation/GitHooks.md)。
- 継続的な統合のための[GitHub Actions](/documentation/GitHubActions.md)。
- メモリリークの検出のための[LeakCanary](https://square.github.io/leakcanary/)。
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)および[Room](https://developer.android.com/training/data-storage/room)の依存関係（必要に応じてsetup.gradleで削除可能）。
- [Paparazzi](https://github.com/cashapp/paparazzi)の依存関係（必要に応じてsetup.gradleで削除可能）。
- [Dokka](https://github.com/Kotlin/dokka) 依存関係、すべてのプロジェクトとモジュールをドキュメント化します。
- [Spotless](https://github.com/diffplug/spotless) 依存関係、コードを清潔に保ちます。
- [sortDependencies](https://github.com/square/gradle-dependencies-sorter) 依存関係、build.gradleファイル内の依存関係を整理します。

## 依存関係の設定

依存関係は[/buildscripts](/buildscripts)に構造化されています。アプリモジュールの依存関係は、[libs.versions.toml](gradle/libs.versions.toml)にあるGradleバージョンカタログを使用して定義されています。

## Danger チェック

PRチェックに[Danger](https://danger.systems)を使用しています。[Dangerfile](Dangerfile)を確認してください。GitHub Actionsでシームレスに動作させるには、GitHubシークレットにDanger APIキーを設定してください。

## テンプレート

整理されたPRの説明のための[Pull Request Template](/.github/pull_request_template.md)を含みます。
