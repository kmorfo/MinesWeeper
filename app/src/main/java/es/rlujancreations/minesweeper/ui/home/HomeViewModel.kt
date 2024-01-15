package es.rlujancreations.minesweeper.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rlujancreations.minesweeper.data.Level
import es.rlujancreations.minesweeper.domain.DatabaseService
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Raúl L.C. on 3/1/24.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val databaseServiceImpl: DatabaseService
) :
    ViewModel() {

    private var _recordsState = MutableStateFlow(RecordState())
    val recordsState: StateFlow<RecordState> = _recordsState

    init {
        loadRecords(level = Level.Easy)
        loadRecords(level = Level.Medium)
        loadRecords(level = Level.Hard)
    }

    private fun loadRecords(level: Level) {
        viewModelScope.launch {
            val record = async {
                databaseServiceImpl.getRecordByLevel(level = level).first()
            }.await()
            _recordsState.value =  when (level) {
                Level.Easy -> _recordsState.value.copy(easy = record)
                Level.Medium -> _recordsState.value.copy(medium = record)
                Level.Hard -> _recordsState.value.copy(hard = record)
            }
        }
    }
}

data class RecordState(
    val easy: String = "9999",
    val medium: String = "9999",
    val hard: String = "9999",
)

