package es.rlujancreations.minesweeper.ui.game

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

/**
 * Created by Raúl L.C. on 3/1/24.
 */
@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel(),
    level: Int,
    navigateToHome: () -> Unit
) {
Text(text = "Game")

}