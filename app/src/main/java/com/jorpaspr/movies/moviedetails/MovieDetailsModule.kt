package com.jorpaspr.movies.moviedetails

import android.content.Context
import com.jorpaspr.movies.database.MoviesDatabase
import com.jorpaspr.movies.database.UserMovie
import com.jorpaspr.movies.moviedetails.mvp.MovieDetailsModel
import com.jorpaspr.movies.moviedetails.mvp.MovieDetailsPresenter
import com.jorpaspr.movies.moviedetails.mvp.view.MovieDetailsView
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsModule(private val context: Context, private val userMovie: UserMovie) {

    @MovieDetailsScope
    @Provides
    fun provideMovieDetailsModel(database: MoviesDatabase) = MovieDetailsModel(database, userMovie)

    @MovieDetailsScope
    @Provides
    fun provideMovieDetailsView() = MovieDetailsView(context)

    @MovieDetailsScope
    @Provides
    fun provideMovieDetailsPresenter(model: MovieDetailsModel, view: MovieDetailsView) = MovieDetailsPresenter(model, view)
}
