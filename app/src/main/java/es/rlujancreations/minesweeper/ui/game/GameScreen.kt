package es.rlujancreations.minesweeper.ui.game

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.ui.theme.Orbitron

/**
 * Created by Raúl L.C. on 3/1/24.
 */
@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel(),
    level: Level,
    navigateToHome: () -> Unit
) {
    LaunchedEffect(true) {
        gameViewModel.createNewGame(level)
    }
    val timeCounter: Int by gameViewModel.timeCounter.collectAsState()

    Text(text = "$timeCounter", fontFamily = Orbitron)


}