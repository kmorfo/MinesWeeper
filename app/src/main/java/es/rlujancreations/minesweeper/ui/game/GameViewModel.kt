package es.rlujancreations.minesweeper.ui.game

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.data.Board
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.domain.DatabaseService
import es.rlujancreations.minesweeper.domain.model.Cell
import es.rlujancreations.minesweeper.domain.model.CellStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 3/1/24.
 */
@HiltViewModel
class GameViewModel @Inject constructor(
    val gameBoard: Board,
    private val databaseServiceImpl: DatabaseService
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    var level: Level = Level.Easy
    private var _cellsWithMines: MutableList<Cell> = mutableListOf()
    private var _cellsMarkedByUser: MutableSet<Cell> = mutableSetOf()
    private lateinit var _bestTime: String

    fun createNewGame(newLevel: Level) {
        level = newLevel
        loadRecords(level = level)
        initGame(level)
    }

    fun restartGame() {
        initGame(level)
        _uiState.value = uiState.value.copy(timeCounter = 0)
    }

    private fun initGame(level: Level) {
        gameBoard.initialize(level)

        _uiState.value = uiState.value.copy(
            remainingMines = gameBoard.getMines(),
            gameStatus = GameStatus.Running
        )
        _cellsMarkedByUser = mutableSetOf()
        initCells()
        startCounter()
    }

    private fun initCells() {
        val updatedCells = Array(level.rows) { row ->
            Array(level.columns) { column ->
                val mines = gameBoard.getCell(row, column)
                if (mines == -1) _cellsWithMines.add(
                    Cell(x = row, y = column, mines = mines, status = CellStatus.Untouched)
                )
                Cell(x = row, y = column, mines = mines, status = CellStatus.Untouched)
            }
        }
        _uiState.value = uiState.value.copy(cells = updatedCells)
    }


    private fun startCounter() {
        viewModelScope.launch(Dispatchers.Main) {
            while (_uiState.value.gameStatus == GameStatus.Running) {
                _uiState.value = uiState.value.copy(timeCounter = uiState.value.timeCounter.plus(1))
                delay(1000)
            }
        }
    }

    fun changePauseStatus() {
        if (_uiState.value.gameStatus == GameStatus.Losed || uiState.value.gameStatus == GameStatus.Winned) {
            restartGame()
            return
        }
        _uiState.value = uiState.value.copy(
            gameStatus =
            if (_uiState.value.gameStatus == GameStatus.Running) GameStatus.Paused else GameStatus.Running
        )
        if (_uiState.value.gameStatus == GameStatus.Running) startCounter()
    }

    fun onLongClick(cell: Cell, showInfoUser: () -> Unit) {
        if (cell.status == CellStatus.Marked) {
            _uiState.value =
                uiState.value.copy(remainingMines = uiState.value.remainingMines.plus(1))
            cell.status = CellStatus.Untouched
            _cellsMarkedByUser.remove(cell)
        } else if (_uiState.value.remainingMines > 0) {
            _uiState.value =
                uiState.value.copy(remainingMines = uiState.value.remainingMines.minus(1))
            cell.status = CellStatus.Marked
            _cellsMarkedByUser.add(cell)
            checkIfGameWin()
        } else showInfoUser()

    }

    fun onClick(cell: Cell) {
        //TODO check game status
        if (cell.mines == -1) {
            _uiState.value = uiState.value.copy(gameStatus = GameStatus.Losed)
            cell.status = CellStatus.Discovered
            return
        }
        if (cell.mines == 0) {
            cell.status = CellStatus.Discovered
            showCells(cell)
            return
        }
        if (cell.mines > 0) {
            cell.status = CellStatus.Discovered
            return
        }
    }

    private fun checkIfGameWin() {
        var minesMarkedCorrecty: Int = 0
        _cellsMarkedByUser.map { cell -> if (cell.mines == -1) minesMarkedCorrecty++ }
        if (minesMarkedCorrecty == level.mines) {
            if (_uiState.value.timeCounter < _bestTime.toInt()) saveRecord(_uiState.value.timeCounter.toString())
            _uiState.value = uiState.value.copy(gameStatus = GameStatus.Winned)
        }
    }

    private fun showCells(touched: Cell) {
        val cellX: Int = touched.x
        val cellY: Int = touched.y

        for (tempX in cellX - 1..cellX + 1)
            for (tempY in cellY - 1..cellY + 1) {
                if (tempX < 0 || tempX >= level.rows || tempY < 0 || tempY >= level.columns) continue
                if (uiState.value.cells[tempX][tempY].mines != -1 && uiState.value.cells[tempX][tempY].status == CellStatus.Untouched) {
                    onClick(cell = uiState.value.cells[tempX][tempY])
                }
            }
    }

    private fun saveRecord(time: String) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseServiceImpl.saveRecordLevel(time = time, level = level)
        }
    }

    private fun loadRecords(level: Level) {
        viewModelScope.launch {
            _bestTime = async {
                databaseServiceImpl.getRecordByLevel(level = level).first()
            }.await()
        }
    }
}

data class GameUiState(
    val gameStatus: GameStatus = GameStatus.Running,
    val timeCounter: Int = 0,
    val remainingMines: Int = 0,
    val cells: Array<Array<Cell>> = emptyArray()
)

sealed class GameStatus(
    @DrawableRes val icon: Int,
    @DrawableRes val image: Int,
    @StringRes val description: Int
) {
    object Paused : GameStatus(
        icon = R.drawable.face_sleep,
        image = R.drawable.face_sleep,
        description = R.string.face_sleeping
    )

    object Running : GameStatus(
        icon = R.drawable.face_smiling,
        image = R.drawable.face_smiling,
        description = R.string.face_smiling
    )

    object Winned : GameStatus(
        icon = R.drawable.face_partying,
        image = R.drawable.ic_happy_mine,
        description = R.string.face_partying
    )

    object Losed : GameStatus(
        icon = R.drawable.face_dizzy,
        image = R.drawable.ic_sad_mine,
        description = R.string.face_dizzy
    )

}