import SwiftUI
import common

@main
struct iOSApp: App {
    init() {
        KoinModulesKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
