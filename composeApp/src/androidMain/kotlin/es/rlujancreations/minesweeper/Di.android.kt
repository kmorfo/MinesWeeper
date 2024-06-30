package es.rlujancreations.minesweeper

import android.content.Context
import es.rlujancreations.minesweeper.ui.core.AndroidScreenDimensions
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 29/6/24.
 */
actual val nativeModule = module {
//    single<Context> { androidContext() }
    single { AndroidScreenDimensions(androidContext()) }
}