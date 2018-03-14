package com.jorpaspr.movies.main.mvp

import android.util.Log
import com.jorpaspr.movies.base.BasePresenter
import com.jorpaspr.movies.main.mvp.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val model: MainModel, private val view: MainView) : BasePresenter() {

    companion object {
        private val LOG_TAG = MainPresenter::class.java.simpleName
    }

    override fun onCreate() {
        view.showLoading()
        loadMovies()
        updateMovies()
    }

    private fun loadMovies() {
        val disposable = model.loadUserMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userMovies ->
                            if (userMovies.isEmpty()) {
                                view.showNoUserMovies()
                            } else {
                                view.showUserMovies(userMovies)
                            }
                        })
        addDisposable(disposable)
    }

    private fun updateMovies() {
        val disposable = model.searchMovies()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { movie ->
                            Log.d(LOG_TAG, "Movie persisted: ${movie.imdbId}")
                        },
                        { _ ->
                            Log.d(LOG_TAG, "Failed retrieving movie from network and persisting it")
                        },
                        {
                            Log.d(LOG_TAG, "On complete")
                        })
        addDisposable(disposable)
    }
}
