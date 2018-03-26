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
import org.mockito.BDDMockito
import org.mockito.Mock
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
        BDDMockito.given(model.loadUserMovies()).willReturn(Flowable.just(userMovies))
        BDDMockito.given(model.searchMovies()).willReturn(Observable.just(createMovie()))

        // when
        presenter.onCreate()

        // then
        BDDMockito.then(view).should(BDDMockito.times(1)).showLoading()
        BDDMockito.then(view).should(BDDMockito.times(1)).showUserMovies(userMovies)
        BDDMockito.then(view).shouldHaveNoMoreInteractions()
    }

    @Test
    fun shouldHandleNoUserMovies() {
        // given
        val userMovies = emptyList<UserMovie>()
        BDDMockito.given(model.loadUserMovies()).willReturn(Flowable.just(userMovies))
        BDDMockito.given(model.searchMovies()).willReturn(Observable.empty())

        // when
        presenter.onCreate()

        // then
        BDDMockito.then(view).should(BDDMockito.times(1)).showLoading()
        BDDMockito.then(view).should(BDDMockito.times(1)).showNoUserMovies()
        BDDMockito.then(view).shouldHaveNoMoreInteractions()
    }
}
