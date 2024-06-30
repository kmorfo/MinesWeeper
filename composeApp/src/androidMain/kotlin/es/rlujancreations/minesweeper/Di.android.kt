package es.rlujancreations.minesweeper

import es.rlujancreations.minesweeper.ui.core.AndroidScreenDimensions
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 29/6/24.
 */
actual val nativeModule = module {
    single { AndroidScreenDimensions(androidContext()) }
}