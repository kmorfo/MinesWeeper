package es.rlujancreations.minesweeper.data



/**
 * Created by Raúl L.C. on 14/1/24.
 */
//class DatabaseServiceImpl (private val context: Context) : DatabaseService {
//    companion object {
//        private val LEVEL_EASY = stringPreferencesKey("easy")
//        private val LEVEL_MEDIUM = stringPreferencesKey("medium")
//        private val LEVEL_HARD = stringPreferencesKey("hard")
//    }
//
//    private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
//        name = "user"
//    )
//
//    override suspend fun saveRecordLevel(time: String, level: Level) {
//        context.userPreferencesDataStore.edit { preferences ->
//            preferences[getStringPrefByLevel(level)] = time
//        }
//    }
//
//    override suspend fun clearRecordLevel(level: Level) {
//        context.userPreferencesDataStore.edit { preferences ->
//            preferences[getStringPrefByLevel(level)] = "9999"
//        }
//    }
//
//    override fun getRecordByLevel(level: Level): Flow<String> {
//        return context.userPreferencesDataStore.data.map { preferences ->
//            preferences[getStringPrefByLevel(level)] ?: "9999"
//        }
//    }
//
//    private fun getStringPrefByLevel(level: Level): Preferences.Key<String> {
//        return when (level) {
//            Level.Easy -> LEVEL_EASY
//            Level.Hard -> LEVEL_MEDIUM
//            Level.Medium -> LEVEL_HARD
//        }
//    }
//}