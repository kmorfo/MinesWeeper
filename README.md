<p align="center">
  <a href="https://rlujancreations.es/" target="blank"><img src="./githubimages/logo.png" width="300px" alt="RLujanCreations Logo" /></a>
</p>

---

# MinesWeeper

This is my version of the classic game Minesweeper, developed in Kotlin.

**Updated to Compose Multi Platform**: This game is now available on Android and iOS.

The original version of this project with the Android version is available on the branch [**Kotlin**](https://github.com/kmorfo/MinesWeeper/tree/Kotlin).

## Main Features and Technologies Used

- **KMP**: Compose Multi Platform
- **Kotlin 2.0**: Utilized for its modern features and performance improvements, making it easier to develop a robust and efficient application.
- **Jetpack Compose**: The user interface is developed using this modern Android UI toolkit, enabling the creation of flexible and dynamic interfaces.
- **MVVM**: Implements the Model-View-ViewModel pattern to separate presentation logic from business logic and data, improving testability and code organization.
- **Dependency Injection - Koin** ~~(formerly Dagger-Hilt)~~: Simplifies dependency management, allowing for better scalability and unit testing.
- **Unit Testing**: User interface tests ensure the app behaves correctly and provides a smooth user experience.
- **Flows**: Handles asynchronous data streams, providing a clean way to manage state and data flow within the application.
- **DataStore Preferences**: Used to save the user's best times locally.

---

## Screenshots

|                 **Home**                |                **Game**                |
|:---------------------------------------:|:--------------------------------------:|
| ![Home Screen](./githubimages/home.png) | ![Game Screen](./githubimages/game.png) |
|                 **iOS Menu**            |                **iOS Game**            |
| ![iOS Menu](./githubimages/iosmenu.png) | ![iOS Game](./githubimages/iosgame.png) |
|                 **Game Win**            |                 **Game Loss**          |
| ![Game Win](./githubimages/wingame.png) | ![Game Loss](./githubimages/losegame.png) |
|                **Both Systems**         |                                         |
|            ![Both Systems](./githubimages/bothsystems.png)            |                                         |                                                
---

## To-Do List

- [X] App Icon on iOS and Android
- [X] Convert to KMP
- [X] End of game **status**
- [X] Dialog for End of Game Status
- [X] Toast alerts when the user has no more mines
- [X] Animations in dialogs
- [X] Persistence for saving records
- [X] Show records on HomeScreen
- [X] Board test created

---
