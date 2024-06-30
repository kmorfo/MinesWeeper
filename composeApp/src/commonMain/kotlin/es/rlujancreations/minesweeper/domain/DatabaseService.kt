package es.rlujancreations.minesweeper.domain

import es.rlujancreations.minesweeper.data.Level
import kotlinx.coroutines.flow.Flow

/**
 * Created by Raúl L.C. on 14/1/24.
 */

interface DatabaseService {
    suspend fun saveRecordLevel(time: String, level: Level)
    suspend fun clearRecordLevel(level: Level)
    fun getRecordByLevel(level: Level): Flow<String>
}
