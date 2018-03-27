package com.jorpaspr.movies.database

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMoviesDatabase(context: Context): MoviesDatabase =
            Room.databaseBuilder(context, MoviesDatabase::class.java, "movies.db")
                    .build()
}
