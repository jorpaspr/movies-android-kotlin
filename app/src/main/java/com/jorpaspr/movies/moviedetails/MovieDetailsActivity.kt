package com.jorpaspr.movies.moviedetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jorpaspr.movies.R
import com.jorpaspr.movies.app.MoviesApp
import com.jorpaspr.movies.database.UserMovie
import com.jorpaspr.movies.moviedetails.mvp.MovieDetailsPresenter
import com.jorpaspr.movies.moviedetails.mvp.view.MovieDetailsView
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
        super.onCreate(savedInstanceState)

        if (!intent.hasExtra(EXTRA_USER_MOVIE)) {
            throw IllegalArgumentException("Activity is missing extra user movie parameter")
        }
        val userMovie: UserMovie = intent.extras.getParcelable<UserMovie>(EXTRA_USER_MOVIE)
        MoviesApp.createMovieDetailsComponent(this, userMovie).inject(this)

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
