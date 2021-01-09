package com.example.moviemania.di

import android.content.Context
import androidx.room.Room
import com.example.moviemania.database.MovieDao
import com.example.moviemania.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun providesDb(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            MoviesDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    @Singleton
    @Provides
    fun providesDao(database: MoviesDatabase) : MovieDao = database.movieDao
}