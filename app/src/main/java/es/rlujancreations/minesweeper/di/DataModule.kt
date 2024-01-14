package es.rlujancreations.minesweeper.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.rlujancreations.minesweeper.data.DatabaseServiceImpl
import es.rlujancreations.minesweeper.domain.DatabaseService
import javax.inject.Singleton

/**
 * Created by Raúl L.C. on 14/1/24.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDataStoreService(@ApplicationContext context: Context): DatabaseService {
        return DatabaseServiceImpl(context)
    }
}