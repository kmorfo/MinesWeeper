package es.rlujancreations.minesweeper.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.minesweeper.R
import es.rlujancreations.minesweeper.data.Board
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.ui.theme.BoardBackground
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
            while (true) {
                _timeCounter.value++
                delay(1000)
            }
        }
    }
}


