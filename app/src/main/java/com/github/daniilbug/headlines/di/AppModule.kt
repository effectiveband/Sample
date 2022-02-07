package com.github.daniilbug.headlines.di

import com.github.daniilbug.core.di.InjectedKey
import com.github.daniilbug.core.navigation.flow.FlowBinder
import com.github.daniilbug.core.navigation.flow.FlowCoordinator
import com.github.daniilbug.headlines.BuildConfig
import com.github.daniilbug.headlines.navigation.ActivityFlowCoordinator
import com.github.daniilbug.headlines.navigation.NavigationFlowMapper
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Named

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Named(InjectedKey.Configuration.VERSION_NAME)
    fun provideAppVersionName(): String = BuildConfig.VERSION_NAME

    @JvmStatic
    @Provides
    fun provideScreenMapper(): NavigationFlowMapper = NavigationFlowMapper()

    @JvmStatic
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @JvmStatic
    @Provides
    @Reusable
    fun provideFlowBinder(coordinator: ActivityFlowCoordinator): FlowBinder = coordinator.Binder()

    @JvmStatic
    @Provides
    fun provideFlowCoordinator(coordinator: ActivityFlowCoordinator): FlowCoordinator = coordinator
}