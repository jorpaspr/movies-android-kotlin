package com.jorpaspr.movies.moviedetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jorpaspr.movies.R
import com.jorpaspr.movies.moviedetails.mvp.MovieDetailsPresenter
import com.jorpaspr.movies.moviedetails.mvp.view.MovieDetailsView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER_MOVIE = "user_movie"
    }

    @Inject
    lateinit var view: MovieDetailsView

    @Inject
    lateinit var presenter: MovieDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(view)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.empty_string)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
