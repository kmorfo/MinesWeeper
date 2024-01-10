package es.rlujancreations.minesweeper.ui.game

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.data.Board
import es.rlujancreations.minesweeper.data.Cell
import es.rlujancreations.minesweeper.data.CellStatus
import es.rlujancreations.minesweeper.data.Level
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 3/1/24.
 */
@HiltViewModel
class GameViewModel @Inject constructor(val gameBoard: Board) : ViewModel() {
    private var _gameStatus = MutableStateFlow<GameStatus>(GameStatus.Running)
    val gameStatus: StateFlow<GameStatus> = _gameStatus

    private var _timeCounter = MutableStateFlow<Int>(0)
    val timeCounter: StateFlow<Int> = _timeCounter

    private var _remainingMines = MutableStateFlow<Int>(0)
    val remainingMines: StateFlow<Int> = _remainingMines

    var level: Level = Level.Easy

    private var _cells = MutableStateFlow<Array<Array<Cell>>>(emptyArray())
    val cells: StateFlow<Array<Array<Cell>>> = _cells


    fun createNewGame(newLevel: Level) {
        level = newLevel
        initGame(level)
    }

    fun restartGame() {
        initGame(level)
        _timeCounter.value = 0
    }

    private fun initGame(level: Level) {
        gameBoard.initialize(level)
        _remainingMines.value = gameBoard.getMines()

        initCells()
        startCounter()
    }

    private fun initCells() {
        val updatedCells = Array(level.rows) { row ->
            Array(level.columns) { column ->
                val mines = gameBoard.getCell(row, column)
                Cell(x = row, y = column, mines = mines, status = CellStatus.Untouched)
            }
        }

        _cells.value = updatedCells
    }


    private fun startCounter() {
        viewModelScope.launch(Dispatchers.Main) {
            while (_gameStatus.value == GameStatus.Running) {
                _timeCounter.value++
                delay(1000)
            }
        }
    }

    fun changePauseStatus() {
        _gameStatus.value =
            if (_gameStatus.value == GameStatus.Running) GameStatus.Paused else GameStatus.Running
        if (_gameStatus.value == GameStatus.Running) startCounter()
    }

    fun onLongClick(cell: Cell) {
        if (cell.status == CellStatus.Marked) {
            _remainingMines.value++
            cell.status = CellStatus.Untouched
        } else if (remainingMines.value > 0) {
            _remainingMines.value--
            cell.status = CellStatus.Marked
        } else {
            //TODO Is necessary notify user because there are no mines available
            Log.d("TEST", "No mines available")
        }
    }

    fun onClick(cell: Cell) {
        //TODO check game status
        if (cell.mines == -1) {
            //TODO Set the end of game and notify user
            Log.d("TEST", "Game lost")
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

    private fun showCells(touched: Cell) {
        val cellX: Int = touched.x
        val cellY: Int = touched.y

        for (tempX in cellX - 1..cellX + 1)
            for (tempY in cellY - 1..cellY + 1) {
                if (tempX < 0 || tempX >= level.rows || tempY < 0 || tempY >= level.columns) continue
                if (_cells.value[tempX][tempY].mines != -1 && _cells.value[tempX][tempY].status == CellStatus.Untouched) {
                    Log.d("INFO","Test X:${tempX} Y:${tempY}")
                    Log.d("INFO","Pulsado ${_cells.value[tempX][tempY].mines}")
                    onClick(cell = _cells.value[tempX][tempY])
                }
            }
    }
}


sealed class GameStatus(@DrawableRes val icon: Int, @StringRes val description: Int) {
    object Paused : GameStatus(icon = R.drawable.face_sleep, description = R.string.face_sleeping)
    object Running : GameStatus(icon = R.drawable.face_smiling, description = R.string.face_smiling)
    object Winned :
        GameStatus(icon = R.drawable.face_partying, description = R.string.face_partying)

    object Losed : GameStatus(icon = R.drawable.face_dizzy, description = R.string.face_dizzy)

}