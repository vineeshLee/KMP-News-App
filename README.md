# News KMP App

This is a Kotlin Multiplatform (KMP) project for a News Application. It's built using Jetpack Compose for the UI and targets Android, iOS, and Desktop (JVM). It follows the MVVM architecture pattern.

## Project Overview

The application aims to provide a seamless news reading experience across multiple platforms using a shared codebase for business logic and UI components where possible.

## Features (based on dependencies)

*   **Cross-Platform UI:** Uses Jetpack Compose for declarative UI development, shared across Android, iOS (via Compose Multiplatform), and Desktop.
*   **Architecture:** Follows the **MVVM** (Model-View-ViewModel) architectural pattern.
*   **Navigation:** Implements navigation within the app using `androidx.navigation:navigation-compose`.
*   **Image Loading:** Uses **`Coil`** for efficient image loading from network sources.
*   **Networking:** Leverages **`Ktor`** for making HTTP requests to fetch news data. It includes support for JSON serialization.
*   **Data Persistence:**
    *   Uses `Room` for local database storage on Android.
    *   Employs Jetpack `DataStore` for storing key-value pairs or typed objects with protocol buffers.
*   **Dependency Injection:** Utilizes **`Koin`** for managing dependencies across the application.
*   **Logging:** Implements `Kermit` for multiplatform logging.
*   **Reactive Programming:** Uses Kotlin Coroutines (including **`kotlinx-coroutines-swing`** for Desktop) for asynchronous operations.
*   **Window Size Management:** Adapts UI based on screen size using `dev.chrisbanes.material3:material3-window-size-class-multiplatform`.
*   **Modern Android Development:**
    *   Uses AndroidX libraries like `Activity Compose`, `Lifecycle ViewModel Compose`, and `Core SplashScreen`.
    *   Targets modern Android SDK versions.
*   **Desktop Support:** Builds native desktop applications (DMG, MSI, Deb formats) using Compose for Desktop.
*   **iOS Support:** Builds an iOS framework for integration into an iOS application.

## Project Structure

The project follows a standard Kotlin Multiplatform structure:

*   `composeApp/src/commonMain`: Contains code shared across all platforms (Android, iOS, Desktop). This includes:
    *   UI components (Composables)
    *   ViewModel logic (implementing **MVVM**)
    *   Networking logic (**`Ktor`**)
    *   Data handling and repositories
    *   Domain models
    *   Navigation graph definitions
*   `composeApp/src/androidMain`: Contains Android-specific code, configurations, and resources.
*   `composeApp/src/iosMain`: Contains iOS-specific code and configurations.
*   `composeApp/src/jvmMain`: Contains Desktop (JVM)-specific code and configurations, including usage of **`kotlinx-coroutines-swing`**.
*   `build.gradle.kts`: The main Gradle build file for the KMP module, defining dependencies and multiplatform configurations.

## Setup and Build

1.  **Clone the repository:**
    
