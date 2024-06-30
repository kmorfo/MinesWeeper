package es.rlujancreations.minesweeper.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


fun dataStoreModule(context: Context): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        produceFile = { context.preferencesDataStoreFile("user.preferences_pb") },
        scope = CoroutineScope(Dispatchers.IO)
    )
}

object DataStoreProvider : KoinComponent {
    private val context: Context by inject()
    val dataStore = dataStoreModule(context)
}

actual fun provideDataStore(): DataStore<Preferences> {
    return DataStoreProvider.dataStore
}
