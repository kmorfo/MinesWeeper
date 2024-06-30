package es.rlujancreations.minesweeper.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.ui.core.Routes.Game
import es.rlujancreations.minesweeper.ui.core.Routes.Help
import es.rlujancreations.minesweeper.ui.core.Routes.Home
import es.rlujancreations.minesweeper.ui.game.GameScreen
import es.rlujancreations.minesweeper.ui.help.HelpScreen
import es.rlujancreations.minesweeper.ui.home.HomeScreen

/**
 * Created by Raúl L.C. on 3/1/24.
 */

@Composable
fun Navigation() {
    val navigationController = rememberNavController()

    NavHost(navController = navigationController, startDestination = Home.route) {
        composable(Home.route) {
            HomeScreen(navigateToGame = { level ->
                navigationController.navigate(
                    Game.createRoute(level)
                )
            }, navigateToHelp = { navigationController.navigate(Help.route) })
        }
        composable(
            Game.route,
            arguments = listOf(navArgument("level") { type = NavType.StringType }),
        ) {
            val levelRoute = it.arguments?.getString("level")
            val level = when (levelRoute) {
                "easy" -> Level.Easy
                "medium" -> Level.Medium
                "hard" -> Level.Hard
                else -> Level.Easy // Default to Easy if route is not recognized
            }
            GameScreen(
                level = level,
                navigateToHome = { navigationController.popBackStack() }
            )
        }
        composable(Help.route) {
            HelpScreen(navigateToHome = { navigationController.popBackStack() })
        }
    }
}

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Game : Routes("game/{level}") {
        fun createRoute(level: Level): String {
            return when (level) {
                is Level.Easy -> "game/easy"
                is Level.Medium -> "game/medium"
                is Level.Hard -> "game/hard"
            }
        }
    }

    object Help : Routes("help")
}
