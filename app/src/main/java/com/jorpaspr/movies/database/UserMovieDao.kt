package com.jorpaspr.movies.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface UserMovieDao {

    @Query("select * from user_movie order by bookmarked desc, imdb_id")
    fun loadAll(): Flowable<List<UserMovie>>

    @Query("select * from user_movie where imdb_id = :imdbId LIMIT 1")
    fun loadByimdbId(imdbId: String): Maybe<UserMovie>

    @Insert(onConflict = REPLACE)
    fun insert(userMovie: UserMovie)

    @Delete
    fun delete(userMovie: UserMovie)

    @Query("delete from user_movie")
    fun deleteAll()
}
