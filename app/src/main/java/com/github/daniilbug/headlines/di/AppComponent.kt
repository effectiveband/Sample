package com.github.daniilbug.headlines.di

import android.app.Application
import android.content.Context
import com.github.daniilbug.headlines.HeadlinesApp
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [],
    dependencies = []
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun cicerone(cicerone: Cicerone<Router>): Builder

        @BindsInstance
        fun applicationContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(app: HeadlinesApp)
}