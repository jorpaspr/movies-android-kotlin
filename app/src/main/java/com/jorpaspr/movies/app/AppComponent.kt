package com.jorpaspr.movies.app

import android.app.Application
import com.jorpaspr.movies.api.ApiModule
import com.jorpaspr.movies.database.DatabaseModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityBuilder::class,
    AppModule::class, ApiModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(app: MoviesApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
