package es.rlujancreations.minesweeper

import es.rlujancreations.minesweeper.data.datastore.provideDataStore
import org.koin.dsl.module

actual val nativeModule =
    module {
        single { provideDataStore() }
    }

