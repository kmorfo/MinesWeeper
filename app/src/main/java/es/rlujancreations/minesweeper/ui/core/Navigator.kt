package es.rlujancreations.minesweeper.ui.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import es.rlujancreations.minesweeper.ui.core.Routes.*
import es.rlujancreations.minesweeper.ui.game.GameScreen
import es.rlujancreations.minesweeper.ui.home.HomeScreen

/**
 * Created by Raúl L.C. on 3/1/24.
 */
@Composable
fun ContentWrapper(navigationController: NavHostController) {
    NavHost(navController = navigationController, startDestination = Home.route) {
        composable(Home.route) {
            HomeScreen(navigateToGame = { level ->
                navigationController.navigate(
                    Game.createRoute(level)
                )
            })
        }
        composable(
            Game.route,
            arguments = listOf(navArgument("level") { type = NavType.IntType }),
        ) {
            GameScreen(
                level = it.arguments?.getInt("level") ?: 0,
                navigateToHome = { navigationController.popBackStack() }
            )
        }
    }
}

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Game : Routes("game/{level}") {
        fun createRoute(level: Int): String {
            return "game/$level"
        }
    }
}
