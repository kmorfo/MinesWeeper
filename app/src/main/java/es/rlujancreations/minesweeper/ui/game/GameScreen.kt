package es.rlujancreations.minesweeper.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.ui.theme.BoardBackground
import es.rlujancreations.minesweeper.ui.theme.HeaderBackground

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
    val remainingMines: Int by gameViewModel.remainingMines.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        GameHeader(timeCounter = timeCounter, remainingMines = remainingMines, modifier = Modifier)
        GameBoard()
    }
}

@Preview(showBackground = true)
@Composable
fun GameBoard(modifier: Modifier = Modifier) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(BoardBackground), Arrangement.SpaceBetween
    ) {}
}

@Composable
fun GameHeader(timeCounter: Int, remainingMines: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(HeaderBackground)
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(6.dp))
        CounterBoard(number = remainingMines)
        Spacer(modifier = Modifier.weight(1f))
        IconFaces() { }
        Spacer(modifier = Modifier.weight(1f))
        CounterBoard(number = timeCounter)
        Spacer(modifier = Modifier.width(6.dp))
    }
}

@Composable
fun IconFaces(modifier: Modifier = Modifier, onIconClick: () -> Unit) {
    Row(
        modifier = modifier
            .background(BoardBackground)
            .size(40.dp)
            .border(1.dp, Color.Gray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier
                .background(BoardBackground)
                .size(36.dp)
                .border(1.dp, Color.Gray)
        ) {
            IconButton(onClick = { onIconClick() }) {
                Image(
                    painter = painterResource(id = R.drawable.face_smiling),
                    contentDescription = stringResource(id = R.string.face_smiling)
                )
            }
        }
    }
}