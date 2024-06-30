package es.rlujancreations.minesweeper.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import es.rlujancreations.minesweeper.domain.DatabaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


/**
 * Created by Raúl L.C. on 14/1/24.
 */
class DatabaseServiceImpl(private val dataStore: DataStore<Preferences>) : DatabaseService {
    companion object {
        private val LEVEL_EASY = stringPreferencesKey("easy")
        private val LEVEL_MEDIUM = stringPreferencesKey("medium")
        private val LEVEL_HARD = stringPreferencesKey("hard")
    }


    override suspend fun saveRecordLevel(time: String, level: Level) {
        dataStore.edit { preferences ->
            preferences[getStringPrefByLevel(level)] = time
        }
    }

    override suspend fun clearRecordLevel(level: Level) {
        dataStore.edit { preferences ->
            preferences[getStringPrefByLevel(level)] = "9999"
        }
    }

    override fun getRecordByLevel(level: Level): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[getStringPrefByLevel(level)] ?: "9999"
        }
    }


    private fun getStringPrefByLevel(level: Level): Preferences.Key<String> {
        return when (level) {
            Level.Easy -> LEVEL_EASY
            Level.Hard -> LEVEL_MEDIUM
            Level.Medium -> LEVEL_HARD
        }
    }
}




