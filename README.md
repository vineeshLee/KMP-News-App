# News KMP App

This is a Kotlin Multiplatform (KMP) project that aims to deliver a modern news reading experience across Android, iOS, and Desktop. It leverages a shared codebase for business logic and UI components where possible, built with Jetpack Compose and following **MVVM** architecture.

## Project Overview

The application fetches news articles, allows users to browse through them, and is designed to be scalable and maintainable across different platforms.


## Screen shorts
   ## Android App
<table>
  <tr>
    <td>
      <img width="270" height="585" alt="search" src="https://github.com/user-attachments/assets/713c6999-121f-4b5d-970e-4cd41391fcbd" />
    </td>
    <td>
      <img width="270" height="585" alt="home" src="https://github.com/user-attachments/assets/4ea38842-9551-4dc9-ad8b-d257b4a41175" />
    </td>
    <td>
      <img width="270" height="585" alt="change_theme" src="https://github.com/user-attachments/assets/bffcb8d9-4d3c-49f1-aa60-030cb9eb6fa5" />
    </td>
  </tr>
</table>

 ## Desktop
 <table>
  <tr>
    <td>
      <img width="735" height="478" alt="Desk_two" src="https://github.com/user-attachments/assets/45868aeb-b9cd-442b-9442-dca119f3bbc3" />
    </td>
    <td>
      <img width="735" height="478" alt="Desk_three" src="https://github.com/user-attachments/assets/77f6ebd9-11a8-44ac-9e21-fbe26861adba" />
    </td>
    <td>
      <img width="735" height="478" alt="Desk_One" src="https://github.com/user-attachments/assets/1d8e1008-d563-495a-bed8-6c07a2a75d21" />
    </td>
  </tr>
</table>




 ## Ios App
 <table>
  <tr>
    <td>
     <img width="540" height="1170" alt="Simulator Screenshot - iPhone 16 Plus - 2025-09-07 at 13 34 05" src="https://github.com/user-attachments/assets/5a21ecb0-0f9d-4539-9eeb-02d051ffa3e4" />
 </td>
    <td>
      <img width="540" height="1170" alt="Simulator Screenshot - iPhone 16 Plus - 2025-09-07 at 13 33 35" src="https://github.com/user-attachments/assets/677e1e11-c81e-4d91-a763-16083291a4ba" />
 </td>
    <td>
     <img width="540" height="1170" alt="Simulator Screenshot - iPhone 16 Plus - 2025-09-07 at 13 33 18" src="https://github.com/user-attachments/assets/e09e59b5-6131-4d26-a241-d4bbe8494c4a" />
 </td>
  </tr>
</table>

## Features

*   **Cross-Platform:** Single codebase for UI (with Compose Multiplatform) and business logic targeting Android, iOS, and Desktop.
*   **Modern UI:** Built entirely with **[Jetpack Compose](https://www.jetbrains.com/lp/compose-multiplatform/)** (and Compose Multiplatform) for a declarative and reactive UI.
*   **MVVM Architecture:** Utilizes the Model-View-ViewModel pattern for a clear separation of concerns in the presentation layer.
*   **Networking:** Fetches news data using **[Ktor](https://ktor.io/)**, a powerful and flexible asynchronous HTTP client framework.
*   **Image Loading:** Efficiently loads and displays images using **[Coil](https://coil-kt.github.io/coil/)**, an image loading library backed by Kotlin Coroutines.
*   **Dependency Injection:** Manages dependencies throughout the application using **[Koin](https://insert-koin.io/)**, a pragmatic lightweight dependency injection framework.
*   **Persistent Storage:**
    *   Uses **[Room](https://developer.android.com/jetpack/androidx/releases/room)** (via JetBrains' multiplatform port) for structured local database storage.
    *   Utilizes **[Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore)** (via JetBrains' multiplatform port) for simple key-value pair storage.
*   **Navigation:** Handles screen transitions using **[Navigation Compose for Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html)**.
*   **Adaptive UI:** Leverages **[Material 3 Window Size Classes](https://developer.android.com/reference/kotlin/androidx/compose/material3/windowsizeclass/package-summary)** (multiplatform version) to adapt layouts to different screen sizes and orientations.
*   **Logging:** Implements multiplatform logging with **[Kermit](https://touchlab.co/kermit)**.

## Tech Stack & Libraries

*   **Core & Language:**
    *   **[Kotlin](https://kotlinlang.org/):** Primary programming language.
    *   **[Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform-started.html):** For sharing code across platforms.
    *   **[Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** For asynchronous programming.
        *   **[kotlinx-coroutines-swing](https://github.com/Kotlin/kotlinx.coroutines/tree/master/ui/kotlinx-coroutines-swing):** Coroutines integration for Swing applications (Desktop).
    *   **[Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization):** For JSON parsing and data serialization.
*   **UI & Presentation:**
    *   **[Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/):** Declarative UI toolkit for Android, iOS, and Desktop.
        *   `Runtime`, `Foundation`, `UI`, `Material 3`, `Material Icons Extended`
    *   **[Navigation Compose for Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html):** For navigating between screens.
    *   **[Coil (Compose Multiplatform)](https://coil-kt.github.io/coil/compose_multiplatform/):** Image loading library.
    *   **[Lifecycle ViewModel Compose (Multiplatform)](https://developer.android.com/jetpack/androidx/releases/lifecycle):** (Using JetBrains' multiplatform ports/equivalents) Supports the MVVM pattern.
    *   **[Material 3 Window Size Class (Multiplatform)](https://github.com/chrisbanes/material3-window-size-class-multiplatform):** For adaptive UIs.
*   **Networking:**
    *   **[Ktor](https://ktor.io/):** HTTP client for fetching data.
        *   `client-core`, `client-json`, `client-logging`, `client-content-negotiation`
        *   Platform-specific engines: `client-android`, `client-darwin` (iOS), `client-okhttp` (JVM)
*   **Data & Persistence:**
    *   **[Room (Multiplatform via androidx.room)](https://developer.android.com/jetpack/androidx/releases/room):** Persistence library for SQLite. (Uses `androidx.sqlite:sqlite-bundled`)
    *   **[DataStore (Multiplatform via androidx.datastore)](https://developer.android.com/topic/libraries/architecture/datastore):** For key-value and typed data storage.
*   **Dependency Injection:**
    *   **[Koin](https://insert-koin.io/):** Lightweight dependency injection framework.
        *   `koin-core`, `koin-compose`, `koin-compose-viewmodel`
*   **Logging:**
    *   **[Kermit](https://touchlab.co/kermit):** Multiplatform logging utility.
*   **Android Specific:**
    *   **[AndroidX Activity Compose](https://developer.android.com/jetpack/androidx/releases/activity):** Integration with Android Activities.
    *   **[AndroidX Core Splashscreen](https://developer.android.com/develop/ui/views/launch/splash-screen):** For app launch splash screens.
*   **Build & Tooling:**
    *   **[Gradle](https://gradle.org/):** Build automation tool.
    *   **[KSP (Kotlin Symbol Processing)](https://kotlinlang.org/docs/ksp-overview.html):** For annotation processing (used by Room).

## Architecture

This application largely follows the **MVVM (Model-View-ViewModel)** architectural pattern.

*   **Model:** Represents the data and business logic. This includes data sources (network via Ktor, local database via Room/DataStore) and repositories that abstract these sources.
*   **View:** The UI components (Composables built with Jetpack Compose) that display information to the user and capture user input.
*   **ViewModel:** Acts as a bridge between the View and the Model. It prepares and manages the data for the View (often exposing it via StateFlows or similar observable types) and handles user interactions by delegating actions to the appropriate business logic or use cases.

Dependency Injection with **Koin** is used to provide dependencies (like ViewModels, Repositories, API services) where needed.

## Project Structure

The project follows a standard Kotlin Multiplatform structure:

*   `composeApp/src/commonMain`: Contains code shared across all platforms (Android, iOS, Desktop). This is where most of the UI (Composables), ViewModels, networking logic (Ktor), data handling, domain models, and navigation logic reside.
*   `composeApp/src/androidMain`: Android-specific code, configurations (like `AndroidManifest.xml`), and resources.
*   `composeApp/src/iosMain`: iOS-specific code, configurations, and any platform-specific implementations.
*   `composeApp/src/jvmMain`: Desktop (JVM)-specific code, configurations, and the main entry point (`MainKt`).
*   `build.gradle.kts`: The main Gradle build file for the KMP module, defining dependencies and multiplatform configurations.

## Setup and Build
1.  **Clone the repository:**


    
