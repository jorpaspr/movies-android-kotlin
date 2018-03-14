package com.jorpaspr.movies.main.mvp

import com.jorpaspr.movies.api.Movie
import com.jorpaspr.movies.database.UserMovie
import com.jorpaspr.movies.main.mvp.MainPresenterTest.Factory.Companion.createMovie
import com.jorpaspr.movies.main.mvp.MainPresenterTest.Factory.Companion.createUserMovie
import com.jorpaspr.movies.main.mvp.view.MainView
import com.jorpaspr.movies.util.RxImmediateSchedulerRule
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit

class MainPresenterTest {

    class Factory {

        companion object {

            fun createMovie(): Movie {
                return Movie("", "", "", "", "", "")
            }

            fun createUserMovie(): UserMovie {
                return UserMovie("", "", "", "", "", "", false)
            }
        }
    }

    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()
        @Rule get

    val mockitoRule = MockitoJUnit.rule()
        @Rule get

    @Mock
    private lateinit var model: MainModel

    @Mock
    private lateinit var view: MainView

    private lateinit var presenter: MainPresenter

    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = MainPresenter(model, view)
    }

    @Test
    fun shouldPassUserMoviesToView() {
        // given
        val userMovies = listOf(createUserMovie(), createUserMovie(), createUserMovie())

        // when
        Mockito.`when`(model.loadUserMovies()).thenReturn(Flowable.just(userMovies))
        Mockito.`when`(model.searchMovies()).thenReturn(Observable.just(createMovie()))
        presenter.onCreate()

        // then
        Mockito.verify(view, Mockito.times(1)).showLoading()
        Mockito.verify(view, Mockito.times(1)).showUserMovies(userMovies)
        Mockito.verifyNoMoreInteractions(view)
    }

    @Test
    fun shouldHandleNoUserMovies() {
        // given
        val userMovies = emptyList<UserMovie>()

        // when
        Mockito.`when`(model.loadUserMovies()).thenReturn(Flowable.just(userMovies))
        Mockito.`when`(model.searchMovies()).thenReturn(Observable.empty())
        presenter.onCreate()

        // then
        Mockito.verify(view, Mockito.times(1)).showLoading()
        Mockito.verify(view, Mockito.times(1)).showNoUserMovies()
        Mockito.verifyNoMoreInteractions(view)
    }
}
