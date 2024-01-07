package es.rlujancreations.minesweeper.ui.game

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.data.Board
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
class GameViewModel @Inject constructor(private val gameBoard: Board) : ViewModel() {
    var _gameStatus = MutableStateFlow<GameStatus>(GameStatus.Running)
    val gameStatus: StateFlow<GameStatus> = _gameStatus

    var _timeCounter = MutableStateFlow<Int>(0)
    val timeCounter: StateFlow<Int> = _timeCounter

    var _remainingMines = MutableStateFlow<Int>(0)
    val remainingMines: StateFlow<Int> = _remainingMines


    fun createNewGame(level: Level) {
        gameBoard.initialize(level)
        _remainingMines.value = gameBoard.getMines()

        startCounter()
    }


    private fun startCounter() {
        viewModelScope.launch(Dispatchers.Main) {
            while (_gameStatus.value == GameStatus.Running) {
                _timeCounter.value++
                delay(1000)
            }
        }
    }

    fun onFaceIconPressed() {
        _gameStatus.value =
            if (_gameStatus.value == GameStatus.Running) GameStatus.Paused else GameStatus.Running
        if (_gameStatus.value == GameStatus.Running) startCounter()
    }

}


sealed class GameStatus(@DrawableRes val icon: Int, @StringRes val description: Int) {
    object Paused : GameStatus(icon = R.drawable.face_sleep, description = R.string.face_sleeping)
    object Running : GameStatus(icon = R.drawable.face_smiling, description = R.string.face_smiling)
    object Winned :
        GameStatus(icon = R.drawable.face_partying, description = R.string.face_partying)

    object Losed : GameStatus(icon = R.drawable.face_dizzy, description = R.string.face_dizzy)

}