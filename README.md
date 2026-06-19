# Listing App

A modern, multi-module Android application showcasing clean architecture, Jetpack Compose, and robust data persistence.

## Features

- **Multi-module Architecture**: Strictly separated layers for better scalability and maintainability.
- **Modern UI**: Built entirely with Jetpack Compose and Material 3.
- **Offline First**: Local data persistence using Room Database with RxJava3 support.
- **Dependency Injection**: Powered by Hilt for clean and testable code.
- **Convention Plugins**: Shared build logic across modules using Gradle Convention Plugins.

## Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Reactive Programming**: [RxJava3](https://github.com/ReactiveX/RxJava)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Build System**: Gradle Kotlin DSL with [KSP](https://kotlinlang.org/docs/ksp-overview.html)

## Project Structure

The project is divided into several modules following the `app` -> `feature` -> `core` hierarchy:

### App
- `:app`: The main entry point of the application.

### Features
Each feature follows an `api`/`impl` separation to promote encapsulation:
- `:feature:listing`: Logic and UI for browsing posts.
- `:feature:detail`: Logic and UI for viewing specific post details and updating title and body.

### Core
- `:core:database`: Room database definitions, DAOs, and entities.
- `:core:data`: Repositories and data source implementations.
- `:core:domain`: Business logic and use cases.
- `:core:model`: Shared data models used across the project.
- `:core:network`: Remote API integration.
- `:core:ui`: Shared UI components and theming.
- `:core:common`: Utility classes and base abstractions.
- `:core:navigation`: Navigation logic between features.

### Build Logic
- `:build-logic:convention`: Custom Gradle plugins for centralized build configuration.

## Setup

1. Clone the repository.
2. Open the project in **Android Studio Ladybug** or newer.
3. Sync Gradle and run the `:app` module.
