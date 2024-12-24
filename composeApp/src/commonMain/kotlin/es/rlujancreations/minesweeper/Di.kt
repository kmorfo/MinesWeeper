package es.rlujancreations.minesweeper

import es.rlujancreations.minesweeper.data.Board
import es.rlujancreations.minesweeper.data.DatabaseServiceImpl
import es.rlujancreations.minesweeper.data.datastore.provideDataStore
import es.rlujancreations.minesweeper.domain.DatabaseService
import es.rlujancreations.minesweeper.ui.game.GameViewModel
import es.rlujancreations.minesweeper.ui.home.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 29/6/24.
 */

val dataStoreModule =
    module {
        single { provideDataStore() }
        single<DatabaseService> { DatabaseServiceImpl(get()) }
    }

val viewModelsModule =
    module {
        single { Board() }

        viewModelOf(::HomeViewModel)
        viewModelOf(::GameViewModel)
    }

expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(dataStoreModule, viewModelsModule, nativeModule)
    }
}
