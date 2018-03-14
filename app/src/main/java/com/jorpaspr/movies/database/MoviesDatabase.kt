package com.jorpaspr.movies.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [UserMovie::class, Bookmark::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun userMovieDao(): UserMovieDao

    abstract fun bookmarkDao(): BookmarkDao
}
