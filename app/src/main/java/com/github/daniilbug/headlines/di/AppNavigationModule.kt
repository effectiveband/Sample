package com.github.daniilbug.headlines.di

import com.github.daniilbug.core.navigation.NavigationBinder
import com.github.daniilbug.core.navigation.cicerone.CiceroneNavigationBinder
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppNavigationModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideNavigationBinder(cicerone: Cicerone<Router>): NavigationBinder {
        return CiceroneNavigationBinder(cicerone.getNavigatorHolder())
    }
}