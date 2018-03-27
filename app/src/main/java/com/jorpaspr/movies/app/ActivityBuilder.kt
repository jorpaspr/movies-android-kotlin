package com.jorpaspr.movies.app

import com.jorpaspr.movies.main.MainActivity
import com.jorpaspr.movies.main.MainModule
import com.jorpaspr.movies.main.MainScope
import com.jorpaspr.movies.moviedetails.MovieDetailsActivity
import com.jorpaspr.movies.moviedetails.MovieDetailsModule
import com.jorpaspr.movies.moviedetails.MovieDetailsScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity

    @MovieDetailsScope
    @ContributesAndroidInjector(modules = [MovieDetailsModule::class])
    abstract fun bindMovieDetailsActivity(): MovieDetailsActivity
}
