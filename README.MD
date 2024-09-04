# FlickFlop

FlickFlop is an Android application for browsing and discovering movies, built with modern Android development practices.

## Features

- Browse popular movies

## Technologies Used

- Kotlin
- Jetpack Compose for UI
- Hilt for dependency injection
- Android Navigation Component
- Material Design 3
- Gradle with Kotlin DSL

## Project Structure

The project is organized into the following main components:

- `app`: Main application module
- `movie_list`: Feature module for displaying the list of movies

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the app on an emulator or physical device

## Building and Running

This project uses Gradle for building. You can build the project using Android Studio or by running the following command in the project root:
```
./gradlew build
```
To run the app, use Android Studio's run feature or use:
```
./gradlew installDebug
```
## Configuration


The app uses The Movie Database (TMDB) API. Make sure to replace the API key in [`MovieService.kt`](https://github.com/deghat-farhad/FlickFlop/blob/master/movie_list/src/main/java/farhad/deghat/flickflop/movie_list/data/remote/services/MoviesService.kt) with your own TMDB API key.


## Project Architecture


The project follows modern Android architecture principles:


- MVVM (Model-View-ViewModel) architecture

- Clean Architecture principles

- Dependency Injection with Hilt

- Jetpack Compose for declarative UI
