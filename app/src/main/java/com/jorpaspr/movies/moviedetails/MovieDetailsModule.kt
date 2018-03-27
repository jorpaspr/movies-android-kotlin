package com.jorpaspr.movies.moviedetails

import com.jorpaspr.movies.database.MoviesDatabase
import com.jorpaspr.movies.database.UserMovie
import com.jorpaspr.movies.moviedetails.mvp.MovieDetailsModel
import com.jorpaspr.movies.moviedetails.mvp.MovieDetailsPresenter
import com.jorpaspr.movies.moviedetails.mvp.view.MovieDetailsView
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsModule {

    @MovieDetailsScope
    @Provides
    fun provideUserMovie(activity: MovieDetailsActivity): UserMovie {
        if (!activity.intent.hasExtra(MovieDetailsActivity.EXTRA_USER_MOVIE)) {
            throw IllegalArgumentException("Activity is missing extra user movie parameter")
        }
        return activity.intent.extras.getParcelable(MovieDetailsActivity.EXTRA_USER_MOVIE)
    }

    @MovieDetailsScope
    @Provides
    fun provideMovieDetailsModel(database: MoviesDatabase, userMovie: UserMovie) = MovieDetailsModel(database, userMovie)

    @MovieDetailsScope
    @Provides
    fun provideMovieDetailsView(activity: MovieDetailsActivity) = MovieDetailsView(activity)

    @MovieDetailsScope
    @Provides
    fun provideMovieDetailsPresenter(model: MovieDetailsModel, view: MovieDetailsView) = MovieDetailsPresenter(model, view)
}
