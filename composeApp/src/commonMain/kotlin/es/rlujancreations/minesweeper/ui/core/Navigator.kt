package es.rlujancreations.minesweeper.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.ui.game.GameScreen
import es.rlujancreations.minesweeper.ui.help.HelpScreen
import es.rlujancreations.minesweeper.ui.home.HomeScreen

/**
 * Created by Raúl L.C. on 3/1/24.
 */

@Composable
fun Navigation() {
    val navigationController = rememberNavController()

    NavHost(navController = navigationController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(
                navigateToGame = { level ->
                    navigationController.navigate(GameRoute(level.toRoute()))
                },
                navigateToHelp = { navigationController.navigate(HelpRoute) }
            )
        }
        composable<GameRoute> {
            val args = it.toRoute<GameRoute>()
            val level = args.level.toLevel()
            GameScreen(level = level, navigateToHome = { navigationController.popBackStack() })
        }
        composable<HelpRoute> {
            HelpScreen(navigateToHome = { navigationController.popBackStack() })
        }
    }
}

private fun Level.toRoute() = when (this) {
    Level.Easy -> "easy"
    Level.Medium -> "medium"
    Level.Hard -> "hard"
}

private fun String.toLevel() = when (this) {
    "easy" -> Level.Easy
    "medium" -> Level.Medium
    "hard" -> Level.Hard
    else -> Level.Easy
}
