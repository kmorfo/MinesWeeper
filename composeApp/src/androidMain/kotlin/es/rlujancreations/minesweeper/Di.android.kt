package es.rlujancreations.minesweeper


import es.rlujancreations.minesweeper.data.datastore.dataStoreModule
import es.rlujancreations.minesweeper.ui.core.AndroidScreenDimensions
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 29/6/24.
 */
actual val nativeModule = module {
    single { dataStoreModule(get()) }
    single { AndroidScreenDimensions(get()) }
}