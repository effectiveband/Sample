package com.github.daniilbug.main.di.navigation

import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.mainNavigation.MainScreen
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [MainNavigationModule::class]
)
interface MainNavigationComponent {

    fun getMainRouter(): AppRouter<MainScreen>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance router: Router): MainNavigationComponent
    }
}