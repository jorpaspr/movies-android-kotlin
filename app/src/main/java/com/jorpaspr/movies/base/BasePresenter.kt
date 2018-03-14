package com.jorpaspr.movies.base

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    open fun onCreate() {
    }

    @CallSuper
    open fun onDestroy() {
        compositeDisposable.clear()
    }

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}
