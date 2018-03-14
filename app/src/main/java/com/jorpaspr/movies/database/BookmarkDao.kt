package com.jorpaspr.movies.database

import android.arch.persistence.room.*
import io.reactivex.Maybe

@Dao
interface BookmarkDao {

    @Query("select * from bookmark where imdb_id = :imdbId LIMIT 1")
    fun loadByimdbId(imdbId: String): Maybe<Bookmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmark: Bookmark)

    @Delete
    fun delete(bookmark: Bookmark)

    @Query("delete from bookmark")
    fun deleteAll()
}
