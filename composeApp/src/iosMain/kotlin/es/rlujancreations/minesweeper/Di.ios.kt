package es.rlujancreations.minesweeper

import es.rlujancreations.minesweeper.data.datastore.provideDataStore
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 29/6/24.
 */
actual val nativeModule =
    module {
        single { provideDataStore() }
    }
