package com.hamy.composeuilearning.localDb

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamy.composeuilearning.ui.network.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TodoModule {

    @Singleton
    @Provides
    fun provideDb(application: Application): TodoDatabase =
        Room.databaseBuilder(application,TodoDatabase::class.java,"TodoDb")
            .fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideToDoDao(db: TodoDatabase) : TodoDao = db.getTodo()

}