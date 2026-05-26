# KmpTemplate

このGitHubリポジトリを使用して、**Kotlin Multiplatform (KMP)** 開発をスタートしましょう。[Hridoy Chandra Das](https://github.com/ihridoydas)によって設計され、Android、iOS、デスクトップ、およびWeb (Wasm) アプリケーションのための堅牢な出発点を提供します。

## なぜ KmpTemplate？

- **デフォルトでマルチプラットフォーム:** Android、iOS、デスクトップ、およびWeb向けの共有ロジックとUI (Compose Multiplatform)。
- **意見を述べるツール:** 設定済みの依存関係管理、gitフック、コードフォーマッティング、静的解析など、開発を向上させるためのツールが含まれています。
- **スマートセットアップ:** プロジェクト名、パッケージIDを変更し、主要なKMPライブラリを数秒で切り替えるためのカスタムスクリプト。

## スタートガイド

1. **"このテンプレートを使用"**をクリックして、アカウントにリポジトリを作成します。
2. `buildscripts/setup.gradle` を開き、プロジェクトの詳細を設定します：
    ```groovy
    def renameConfig = [
        newTemplateName          : "MyAwesomeApp",    // プロジェクト名
        newTemplateAppId         : "com.example.app", // パッケージID
        newMaterialThemeName     : "AppTheme",        // Material Theme名

        // KMPライブラリの切り替え
        useKoin                  : true,
        useKtor                  : true,
        useRoomKmp               : true,
        useComposeMultiplatform  : true,
    ]
    ```
3. ターミナルでセットアップコマンドを実行します：
    ```bash
    ./gradlew renameTemplate
    ```
4. **Android Studioを再起動**し、Gradleを再同期すれば、ビルドの準備は完了です！

## 含まれるもの

共有ロジック、コンポーネント、およびドキュメントを確認してください：

- [Essential KMP Tasks](/documentation/EssentialTasks.md) - プラットフォーム固有のコマンドについては、**ここから始めてください**。
- [Ktlint](/documentation/StaticAnalysis.md) コードフォーマット用。
- [Detekt](/documentation/StaticAnalysis.md) コードスメル検出用。
- [Git Hooks](/documentation/GitHooks.md) プリコミットチェック用。
- [GitHub Actions](/documentation/GitHubActions.md) CI/CD用。
- [Dokka](/documentation/StaticAnalysis.md) APIドキュメント用。

## プロジェクト構造

- `:app`: Android固有のアプリケーションモジュール。
- `:common`: プロジェクトの心臓部。共有UI (Compose) とビジネスロジックが含まれます。
- `:navigation`: 共有ナビゲーション設定。
- `:storage`: 共有ローカルデータ処理 (DataStore/Room)。
- `:theme`: 共有 Material 3 デザインシステム。
