package com.jorpaspr.movies.database

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideMoviesDatabase(): MoviesDatabase =
            Room.databaseBuilder(context, MoviesDatabase::class.java, "movies.db")
                    .build()
}
