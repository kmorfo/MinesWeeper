package es.rlujancreations.minesweeper.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
class GameViewModel @Inject constructor( private val gameBoard: Board) : ViewModel() {
    var _timeCounter = MutableStateFlow<Int>(0)
    val timeCounter: StateFlow<Int> = _timeCounter

    fun createNewGame(level:Level) {
        gameBoard.initialize(level)
        startCounter()
    }



    private fun startCounter() {
        viewModelScope.launch(Dispatchers.Main) {
            while (true) {
                _timeCounter.value++
                delay(1000)
            }
        }
    }


}