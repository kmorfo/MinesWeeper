package es.rlujancreations.minesweeper.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect fun provideDataStore(): DataStore<Preferences>