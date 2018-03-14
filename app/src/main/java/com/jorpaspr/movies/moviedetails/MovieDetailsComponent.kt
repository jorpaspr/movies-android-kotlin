package com.jorpaspr.movies.moviedetails

import dagger.Subcomponent

@MovieDetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsComponent {

    fun inject(activity: MovieDetailsActivity)
}
