package com.jorpaspr.movies.app

import android.app.Application
import com.jorpaspr.movies.api.ApiModule
import com.jorpaspr.movies.database.DatabaseModule
import com.jorpaspr.movies.database.UserMovie
import com.jorpaspr.movies.main.MainActivity
import com.jorpaspr.movies.main.MainComponent
import com.jorpaspr.movies.main.MainModule
import com.jorpaspr.movies.moviedetails.MovieDetailsActivity
import com.jorpaspr.movies.moviedetails.MovieDetailsComponent
import com.jorpaspr.movies.moviedetails.MovieDetailsModule

class MoviesApp : Application() {

    companion object {
        private lateinit var appComponent: AppComponent

        @JvmStatic
        fun createMainComponent(activity: MainActivity): MainComponent =
                appComponent.plusMainComponent(MainModule(activity))

        @JvmStatic
        fun createMovieDetailsComponent(activity: MovieDetailsActivity, userMovie: UserMovie): MovieDetailsComponent =
                appComponent.plusMovieDetailsComponent(MovieDetailsModule(activity, userMovie))
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .apiModule(ApiModule())
                .databaseModule(DatabaseModule(this))
                .build()
    }
}
