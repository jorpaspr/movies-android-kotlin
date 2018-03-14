package com.jorpaspr.movies.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.jorpaspr.movies.R
import com.jorpaspr.movies.app.MoviesApp
import com.jorpaspr.movies.database.UserMovie
import com.jorpaspr.movies.main.mvp.MainPresenter
import com.jorpaspr.movies.main.mvp.view.MainView
import com.jorpaspr.movies.main.mvp.view.MoviesAdapter
import com.jorpaspr.movies.moviedetails.MovieDetailsActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MoviesAdapter.OnMovieClicked, MainView {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var emptyTextView: TextView
    private lateinit var errorTextView: TextView
    private lateinit var indeterminateBar: ProgressBar

    private lateinit var mMoviesAdapter: MoviesAdapter

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recycler_view)
        emptyTextView = findViewById(R.id.empty)
        errorTextView = findViewById(R.id.error)
        indeterminateBar = findViewById(R.id.indeterminateBar)

        mMoviesAdapter = MoviesAdapter(this, this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mMoviesAdapter

        MoviesApp.createMainComponent(this).inject(this)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showUserMovies(userMovies: List<UserMovie>) {
        mMoviesAdapter.setData(userMovies)
        emptyTextView.visibility = View.INVISIBLE
        errorTextView.visibility = View.INVISIBLE
        indeterminateBar.visibility = View.INVISIBLE
        mRecyclerView.visibility = View.VISIBLE
    }

    override fun showNoUserMovies() {
        mRecyclerView.visibility = View.INVISIBLE
        errorTextView.visibility = View.INVISIBLE
        indeterminateBar.visibility = View.INVISIBLE
        emptyTextView.visibility = View.VISIBLE
    }

    override fun showError() {
        mRecyclerView.visibility = View.INVISIBLE
        emptyTextView.visibility = View.INVISIBLE
        indeterminateBar.visibility = View.INVISIBLE
        errorTextView.visibility = View.VISIBLE
    }

    override fun showLoading() {
        mRecyclerView.visibility = View.INVISIBLE
        emptyTextView.visibility = View.INVISIBLE
        errorTextView.visibility = View.INVISIBLE
        indeterminateBar.visibility = View.VISIBLE
    }

    override fun onClick(userMovie: UserMovie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.EXTRA_USER_MOVIE, userMovie)
        startActivity(intent)
    }
}
