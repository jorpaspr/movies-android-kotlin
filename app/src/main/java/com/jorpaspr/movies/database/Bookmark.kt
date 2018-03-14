package com.jorpaspr.movies.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "bookmark")
data class Bookmark(
        @PrimaryKey
        @ColumnInfo(name = "imdb_id")
        val imdbId: String)
