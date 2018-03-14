package com.jorpaspr.movies.app

import com.jorpaspr.movies.api.ApiModule
import com.jorpaspr.movies.api.MoviesClient
import com.jorpaspr.movies.main.MainComponent
import com.jorpaspr.movies.main.MainModule
import com.jorpaspr.movies.moviedetails.MovieDetailsComponent
import com.jorpaspr.movies.moviedetails.MovieDetailsModule
import com.jorpaspr.movies.database.DatabaseModule
import com.jorpaspr.movies.database.MoviesDatabase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, DatabaseModule::class])
interface AppComponent {

    fun plusMainComponent(module: MainModule): MainComponent

    fun plusMovieDetailsComponent(module: MovieDetailsModule): MovieDetailsComponent

    fun moviesClient(): MoviesClient

    fun moviesDatabase(): MoviesDatabase
}
