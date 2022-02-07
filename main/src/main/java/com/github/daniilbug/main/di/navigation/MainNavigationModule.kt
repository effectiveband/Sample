package com.github.daniilbug.main.di.navigation

import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.core.navigation.cicerone.CiceroneAppRouter
import com.github.daniilbug.core.navigation.cicerone.CiceroneScreenMapper
import com.github.daniilbug.main.navigation.MainNavigationMapper
import com.github.daniilbug.mainNavigation.MainScreen
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
interface MainNavigationModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Reusable
        fun provideRouter(
            ciceroneRouter: Router,
            mainMapper: CiceroneScreenMapper<MainScreen>
        ): AppRouter<MainScreen> = CiceroneAppRouter(ciceroneRouter, mainMapper)
    }

    @Binds
    fun bindMainMapper(mapper: MainNavigationMapper): CiceroneScreenMapper<MainScreen>
}