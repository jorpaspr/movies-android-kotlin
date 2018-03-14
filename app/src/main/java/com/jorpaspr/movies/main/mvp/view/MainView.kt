package com.jorpaspr.movies.main.mvp.view

import com.jorpaspr.movies.database.UserMovie

interface MainView {

    fun showUserMovies(userMovies: List<UserMovie>)

    fun showNoUserMovies()

    fun showError()

    fun showLoading()
}
