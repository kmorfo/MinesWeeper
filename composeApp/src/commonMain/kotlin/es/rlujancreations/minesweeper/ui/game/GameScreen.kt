package es.rlujancreations.minesweeper.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.domain.model.Cell
import es.rlujancreations.minesweeper.ui.composables.OutlinedCustomButton
import es.rlujancreations.minesweeper.ui.composables.TextButtonDialog
import es.rlujancreations.minesweeper.ui.core.getScreenDimensions
import es.rlujancreations.minesweeper.ui.game.composables.CellBoard
import es.rlujancreations.minesweeper.ui.game.composables.CounterBoard
import es.rlujancreations.minesweeper.ui.theme.BoardBackground
import es.rlujancreations.minesweeper.ui.theme.CounterFontColor
import es.rlujancreations.minesweeper.ui.theme.DarkBlue
import es.rlujancreations.minesweeper.ui.theme.HeaderBackground
import minesweeper.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

/**
 * Created by Raúl L.C. on 3/1/24.
 */
@OptIn(KoinExperimentalAPI::class)
@Composable
fun GameScreen(
    gameViewModel: GameViewModel = koinViewModel(),
    level: Level,
    navigateToHome: () -> Unit
) {
    LaunchedEffect(true) {
        gameViewModel.createNewGame(level)
    }

    val gameUiState by gameViewModel.uiState.collectAsState()

    PauseDialog(
        gameStatus = gameUiState.gameStatus,
        onDismiss = { gameViewModel.changePauseStatus() },
        navigateToHome = { navigateToHome() },
        restartGame = { gameViewModel.restartGame() }
    )
    ResultDialog(
        gameStatus = gameUiState.gameStatus,
        onDismiss = { gameViewModel.changePauseStatus() },
        restartGame = { gameViewModel.restartGame() }
    )


    Column(modifier = Modifier.fillMaxSize()) {
        GameHeader(
            gameStatus = gameUiState.gameStatus,
            timeCounter = gameUiState.timeCounter,
            remainingMines = gameUiState.remainingMines,
            modifier = Modifier,
            onIconClick = { gameViewModel.changePauseStatus() })

        GameBoard(gameViewModel = gameViewModel, cells = gameUiState.cells)
    }
}


@Composable
fun ResultDialog(
    gameStatus: GameStatus,
    onDismiss: () -> Unit,
    restartGame: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = gameStatus == GameStatus.Winned || gameStatus == GameStatus.Losed,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        Dialog(onDismissRequest = { onDismiss() }) {
            OutlinedCard(
                modifier = modifier,
                border = BorderStroke(1.dp, CounterFontColor)
            ) {
                Text(
                    modifier = Modifier
                        .width(180.dp)
                        .padding(vertical = 8.dp),
                    text = stringResource(gameStatus.description),
                    color = if (gameStatus == GameStatus.Losed) CounterFontColor else DarkBlue,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(gameStatus.image),
                    contentDescription = stringResource(gameStatus.description),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally)
                )
                OutlinedCustomButton(
                    modifier = Modifier.width(180.dp),
                    text = stringResource(Res.string.play_again),
                    color = Color(0xFFF44336),
                    onClick = { restartGame() })
            }
        }
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
    AnimatedVisibility(visible = gameStatus == GameStatus.Paused) {
        Dialog(onDismissRequest = { onDismiss() }) {
            OutlinedCard(
                modifier = modifier,
                colors = CardDefaults.cardColors(containerColor = DarkBlue),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text(
                    text = stringResource(gameStatus.description),
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
                TextButtonDialog(
                    stringResource(Res.string.resume),
                    onClick = { onDismiss() })
                TextButtonDialog(
                    stringResource(Res.string.restart_game),
                    onClick = { restartGame() })
                TextButtonDialog(
                    stringResource(Res.string.return_to_main),
                    onClick = { navigateToHome() })
            }
        }
    }
}

@Composable
fun GameBoard(
    gameViewModel: GameViewModel,
    cells: Array<Array<Cell>>,
    modifier: Modifier = Modifier
) {
    val screenDimensions = remember { getScreenDimensions() }
    val screenWidthDp = screenDimensions.screenWidthDp
    val screenHeightDp = screenDimensions.screenHeightDp
    val cellWith = screenWidthDp / gameViewModel.level.columns
    val cellHeight = (screenHeightDp - 65) / gameViewModel.level.rows

    println("Screen Width $screenWidthDp")
    println("Screen Height $screenHeightDp")

//    val context = LocalContext.current
    val msgNoMines: String = stringResource(Res.string.no_mines)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BoardBackground),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        for (row in cells.indices) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cellHeight.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                for (column in cells[row].indices) {
                    val cell = cells[row][column]
                    CellBoard(
                        cell = cell,
                        modifier = Modifier
                            .width(cellWith.dp)
                            .height(cellHeight.dp),
                        onClick = { gameViewModel.onClick(it) },
                        onLongClick = {
                            //TODO: Info
//                            gameViewModel.onLongClick(cell = it, showInfoUser = {
//                                Toast.makeText(
//                                    context, msgNoMines, Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                    )
                        }
                    )
                }
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
                    painter = painterResource(gameStatus.icon),
                    contentDescription = stringResource(gameStatus.description)
                )
            }
        }
    }
}