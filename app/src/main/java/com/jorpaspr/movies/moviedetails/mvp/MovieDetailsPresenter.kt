package com.jorpaspr.movies.moviedetails.mvp

import android.util.Log
import com.jorpaspr.movies.base.BasePresenter
import com.jorpaspr.movies.moviedetails.mvp.view.MovieDetailsView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsPresenter(private val model: MovieDetailsModel, private val view: MovieDetailsView) : BasePresenter() {

    companion object {
        private val LOG_TAG = this::class.java.simpleName
    }

    private var bookmarked: Boolean = model.userMovie.bookmarked

    override fun onCreate() {
        view.showBookmarked(bookmarked)
        view.showUserMovie(model.userMovie)

        val disposable: Disposable = view.clickBookmarkButtonObservable
                .flatMap { onClickBookmark().subscribeOn(Schedulers.io()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            bookmarked = !bookmarked
                            view.showBookmarked(bookmarked)
                            view.enableBookmarkButton(true)
                            if (bookmarked) {
                                Log.d(LOG_TAG, "Bookmark added")
                            } else {
                                Log.d(LOG_TAG, "Bookmark removed")
                            }
                        },
                        {
                            view.enableBookmarkButton(true)
                            Log.d(LOG_TAG, "Set bookmark failed")
                        }
                )
        addDisposable(disposable)
    }

    private fun onClickBookmark(): Observable<Any> {
        view.enableBookmarkButton(false)

        return if (bookmarked) {
            Log.d(LOG_TAG, "Removing bookmark...")
            model.unBookmarkMovie()
        } else {
            Log.d(LOG_TAG,"Adding bookmark...")
            model.bookmarkMovie()
        }
    }
}
