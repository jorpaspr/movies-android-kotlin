package com.jorpaspr.movies.moviedetails.mvp

import com.jorpaspr.movies.database.Bookmark
import com.jorpaspr.movies.database.MoviesDatabase
import com.jorpaspr.movies.database.UserMovie
import io.reactivex.Observable

class MovieDetailsModel(private val database: MoviesDatabase, val userMovie: UserMovie) {

    fun bookmarkMovie(): Observable<Any> =
            Observable.create<Any> {
                database.bookmarkDao().insert(Bookmark(userMovie.imdbId))
                database.userMovieDao().insert(UserMovie(
                        userMovie.imdbId, userMovie.title, userMovie.actors, userMovie.plot,
                        userMovie.imdbRating, userMovie.poster, true))
                if (!it.isDisposed) {
                    it.onNext(0)
                }
            }

    fun unBookmarkMovie(): Observable<Any> =
            Observable.create<Any> {
                database.bookmarkDao().delete(Bookmark(userMovie.imdbId))
                database.userMovieDao().insert(UserMovie(
                        userMovie.imdbId, userMovie.title, userMovie.actors, userMovie.plot,
                        userMovie.imdbRating, userMovie.poster, false))
                if (!it.isDisposed) {
                    it.onNext(0)
                }
            }
}
