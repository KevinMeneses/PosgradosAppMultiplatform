import SwiftUI
import composeApp

@main
struct iOSApp: App {
    init () {
        KotlinClass.Companion.shared.jwtParser = JWTDecodeWrapper()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
