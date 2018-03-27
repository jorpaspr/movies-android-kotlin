package com.jorpaspr.movies.main

import com.jorpaspr.movies.api.MoviesClient
import com.jorpaspr.movies.database.MoviesDatabase
import com.jorpaspr.movies.main.mvp.MainModel
import com.jorpaspr.movies.main.mvp.MainPresenter
import com.jorpaspr.movies.main.mvp.view.MainView
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideModel(database: MoviesDatabase, client: MoviesClient) = MainModel(database, client)

    @MainScope
    @Provides
    fun provideView(activity: MainActivity) = activity as MainView

    @MainScope
    @Provides
    fun providePresenter(model: MainModel, view: MainView) = MainPresenter(model, view)
}
