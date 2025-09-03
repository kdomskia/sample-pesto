# Kdomskia - Pesto

![Banner](/docs/image/banner.webp)

This is a Kotlin Multiplatform project targeting **Android, iOS, Web (DOM, JS), Desktop (JVM), and Server (Spring Boot)**.  
It is powered by [Kdomskia](https://github.com/kdomskia/kdomskia) to unify UI across platforms.

## Project Structure

* [/composeApp](./composeApp/src) contains the UI and shared code for your applications.  
  It includes several source sets:
    - [commonMain](./composeApp/src/commonMain/kotlin) → shared business logic and UI for all targets.
    - [domMain](./composeApp/src/domMain/kotlin) → code specific to Web (DOM)
    - [skiaMain](./composeApp/src/skiaMain/kotlin) → code specific to Skia-based platforms (Android, iOS, Desktop)
    - [androidMain](./composeApp/src/androidMain/kotlin), [desktopMain](./composeApp/src/desktopMain/kotlin), [iosMain](./composeApp/src/iosMain/kotlin), [jsMain](./composeApp/src/jsMain/kotlin) → platform-specific code

* [/iosApp](./iosApp) contains the iOS application entry point. Even when sharing UI with Compose Multiplatform,  
  you need this module for iOS integration. SwiftUI code can also be added here if required.

* [/server](./server/src/main/kotlin) contains the **Spring Boot** server application.

* [/shared](./shared/src) contains code that is shared across all modules (backend and frontend).  
  The most important subfolder is [commonMain](./shared/src/commonMain/kotlin). Platform-specific folders can also be used if needed.

## Build and Run Server

To build and run the development version of the server (Spring Boot), use the run configuration from the IDE toolbar  
or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :server:bootRun
  ```
- on Windows
  ```shell
  .\gradlew.bat :server:bootRun
  ```

## Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the IDE toolbar  
or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

## Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the IDE toolbar  
or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

## Build and Run Web Application

To build and run the development version of the web app (JS, DOM), use the run configuration from the IDE toolbar  
or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:jsBrowserDevelopmentRun
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:jsBrowserDevelopmentRun
  ```

## Build and Run iOS Application

To build and run the development version of the iOS app, open the [/iosApp](./iosApp) directory in Xcode  
and run it from there. You can also use the run configuration from the IDE toolbar.

## Hot Reload

You can check your layout changes without building the whole project with `Compose Hot Reload`.

To use it, open the `Main` class inside the `desktopMain` source set and click on **Run composeApp with Compose Hot Reload** in your IDE (IntelliJ IDEA or Android Studio).

Simply save the file you are working on, and the running application will be updated automatically.

For more details, see the official JetBrains documentation:
[Hot Reload in Compose Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-hot-reload.html)

## Credits

This app’s design system is derived from the Material 3 Figma prototype published for Flutter Forward 2022: Material 3 from design to deployment.

Check the [Figma prototype](https://www.figma.com/community/file/1199784060037728858).

---

Learn more at:
- [Kdomskia](https://github.com/kdomskia/kdomskia)
- [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform)
