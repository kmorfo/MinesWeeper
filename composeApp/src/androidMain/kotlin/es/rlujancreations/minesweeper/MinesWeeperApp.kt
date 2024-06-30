package es.rlujancreations.minesweeper

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/**
 * Created by Raúl L.C. on 29/6/24.
 */
class MinesWeeperApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MinesWeeperApp)
        }
    }
}