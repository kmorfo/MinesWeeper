<p align="center">
  <a href="https://rlujancreations.es/" target="blank"><img src="./githubimages/logo.png" width="300px" alt="RLujanCreations Logo" /></a>
</p>

---

# MinesWeeper
This is my version of the classic game Minesweeper, developed in Kotlin.

## Main Features and Technologies Used

- **Jetpack Compose**: The user interface is developed using this modern Android UI toolkit, making it easy to create flexible and dynamic interfaces.
- **MVVM**: Implements the Model-View-ViewModel pattern to separate presentation logic from business logic and data, improving testability and code organization.
- **Dependency Injection - Dagger-Hilt**: Used to simplify dependency management, allowing for better scalability and unit testing.
- **Unit Test**: User interface tests to ensure the app behaves correctly and provides a smooth user experience.
- **Flows**: Utilized to handle asynchronous data streams, providing a clean way to manage state and data flow within the application.

---

## Screenshots

| ![Home Screen](./githubimages/home.png) | ![Game Screen](./githubimages/game.png) |
|:---------------------------------------:|:--------------------------------------:|
|                 **Home**                |                **Game**                |

| ![Game Win Screen](./githubimages/wingame.png) | ![Game Loss Screen](./githubimages/losegame.png) |
|:----------------------------------------------:|:------------------------------------------------:|
|                 **Game Win**                   |                 **Game Loss**                    |

---

## ToDo List

- [X] End of game **status**
- [X] Dialog Status End of Game
- [X] Toast for alerts, only when the user has no more mines
- [X] Animations, only in dialogs
- [X] Persistence for saving records
- [X] Show records on HomeScreen
- [X] Board test created

---
