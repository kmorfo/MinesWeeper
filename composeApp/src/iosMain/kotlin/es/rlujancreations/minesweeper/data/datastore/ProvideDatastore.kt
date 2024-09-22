package es.rlujancreations.minesweeper.data.datastore

/**
 * Created by Raúl L.C. on 30/6/24.
 */

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun provideDataStore(): DataStore<Preferences> {
    val documentDirectory: NSURL? =
        NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
    val pathString = requireNotNull(documentDirectory).path + "/user.preferences_pb"
    val path: Path = pathString.toPath()

    return PreferenceDataStoreFactory.createWithPath { path }
}
