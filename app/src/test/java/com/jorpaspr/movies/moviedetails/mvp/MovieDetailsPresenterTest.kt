package com.jorpaspr.movies.moviedetails.mvp

import com.jorpaspr.movies.database.UserMovie
import com.jorpaspr.movies.moviedetails.mvp.MovieDetailsPresenterTest.Factory.Companion.createUserMovie
import com.jorpaspr.movies.moviedetails.mvp.view.MovieDetailsView
import com.jorpaspr.movies.util.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class MovieDetailsPresenterTest {

    class Factory {

        companion object {

            fun createUserMovie(bookmarked: Boolean): UserMovie {
                return UserMovie("", "", "", "", "", "", bookmarked)
            }
        }
    }

    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()
        @Rule get

    val mockitoRule = MockitoJUnit.rule()
        @Rule get

    @Mock
    private lateinit var model: MovieDetailsModel

    @Mock
    private lateinit var view: MovieDetailsView

    private lateinit var presenter: MovieDetailsPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = MovieDetailsPresenter(model, view)
    }

    @Test
    fun shouldPassBookmarkedUserMovieToView() {
        // given
        val userMovie = createUserMovie(true)
        BDDMockito.given(model.userMovie).willReturn(userMovie)

        // when
        presenter.onCreate()

        // then
        BDDMockito.then(view).should(BDDMockito.times(1)).showUserMovie(userMovie)
        BDDMockito.then(view).should(BDDMockito.times(1)).showBookmarked(true)
        BDDMockito.then(view).shouldHaveNoMoreInteractions()
    }

    @Test
    fun shouldPassNotBookmarkedUserMovieToView() {
        // given
        val userMovie = createUserMovie(false)
        BDDMockito.given(model.userMovie).willReturn(userMovie)

        // when
        presenter.onCreate()

        // then
        BDDMockito.then(view).should(BDDMockito.times(1)).showUserMovie(userMovie)
        BDDMockito.then(view).should(BDDMockito.times(1)).showBookmarked(false)
        BDDMockito.then(view).shouldHaveNoMoreInteractions()
    }
}
