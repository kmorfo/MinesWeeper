package es.rlujancreations.minesweeper.ui.game

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.data.Board
import es.rlujancreations.minesweeper.data.Cell
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.ui.composables.TextButtonDialog
import es.rlujancreations.minesweeper.ui.theme.BoardBackground
import es.rlujancreations.minesweeper.ui.theme.DarkBlue
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
    val gameStatus: GameStatus by gameViewModel.gameStatus.collectAsState()


    PauseDialog(
        gameStatus = gameStatus,
        onDismiss = { gameViewModel.changePauseStatus() },
        navigateToHome = { navigateToHome() },
        restartGame = { gameViewModel.restartGame() }
    )


    Column(modifier = Modifier.fillMaxSize()) {
        GameHeader(
            gameStatus = gameStatus,
            timeCounter = timeCounter,
            remainingMines = remainingMines,
            modifier = Modifier,
            onIconClick = { gameViewModel.changePauseStatus() })

        GameBoard(
            gameBoard = gameViewModel.gameBoard,
            level = level,
            onClick = { gameViewModel.onClick(it) },
            onLongClick = { gameViewModel.onLongClick(it) })
    }
}

@Composable
fun PauseDialog(
    gameStatus: GameStatus,
    onDismiss: () -> Unit,
    navigateToHome: () -> Unit,
    restartGame: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (gameStatus == GameStatus.Paused) Dialog(onDismissRequest = { onDismiss() }) {
        OutlinedCard(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = DarkBlue),
            border = BorderStroke(1.dp, Color.White)
        ) {
            Text(
                text = stringResource(id = gameStatus.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 22.sp
            )
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White)
            ) {}
            TextButtonDialog(stringResource(id = R.string.resume), onClick = { onDismiss() })
            TextButtonDialog(
                stringResource(id = R.string.restart_game),
                onClick = { restartGame() })
            TextButtonDialog(
                stringResource(id = R.string.return_to_main),
                onClick = { navigateToHome() })
        }
    }
}

@Composable
fun GameBoard(
    gameBoard: Board,
    level: Level,
    modifier: Modifier = Modifier,
    onClick: (Cell) -> Unit,
    onLongClick: (Cell) -> Unit
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp
    val cellWith = screenWidthDp / level.columns
    val cellHeight = (screenHeightDp - 65) / level.rows
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BoardBackground),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        for (row in 0..<level.rows)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cellHeight.dp)
            ) {
                for (column in 0..<level.columns) {
                    CellBoard(
                        cellIcon = CellIcon.Unclicked,
                        cell = Cell(
                            x = row,
                            y = column,
                            gameBoard.getCell(x = row, y = column)
                        ),
                        modifier = Modifier
                            .width(cellWith.dp)
                            .height(cellHeight.dp),
                        onClick = { onClick(it) },
                        onLongClick = { onLongClick(it) }
                    )
                }
            }
    }
}

@Composable
fun GameHeader(
    gameStatus: GameStatus,
    timeCounter: Int,
    remainingMines: Int,
    modifier: Modifier = Modifier,
    onIconClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(HeaderBackground)
            .border(1.dp, Color.Gray)
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(6.dp))
        CounterBoard(number = remainingMines)
        Spacer(modifier = Modifier.weight(1f))
        IconFaces(gameStatus = gameStatus) { onIconClick() }
        Spacer(modifier = Modifier.weight(1f))
        CounterBoard(number = timeCounter)
        Spacer(modifier = Modifier.width(6.dp))
    }
}

@Composable
fun IconFaces(gameStatus: GameStatus, modifier: Modifier = Modifier, onIconClick: () -> Unit) {
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
                    painter = painterResource(id = gameStatus.icon),
                    contentDescription = stringResource(id = gameStatus.description)
                )
            }
        }
    }
}