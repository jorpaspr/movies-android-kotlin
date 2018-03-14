package com.jorpaspr.movies.main

import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)
}
