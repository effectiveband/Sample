package com.github.daniilbug.headlines.di

import android.content.Context
import com.github.daniilbug.debug.di.DebugMenuModule
import com.github.daniilbug.feed.di.FeedComponentDependencies
import com.github.daniilbug.headlines.HeadlinesApp
import com.github.daniilbug.main.di.MainComponentDependencies
import com.github.daniilbug.main.di.navigation.MainNavigationComponent
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppNavigationModule::class,
        ComponentDependenciesModule::class,
        DebugMenuModule::class
    ],
    dependencies = [MainNavigationComponent::class]
)
interface AppComponent : MainComponentDependencies, FeedComponentDependencies {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun cicerone(cicerone: Cicerone<Router>): Builder

        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun mainNavigationComponent(mainNavigationComponent: MainNavigationComponent): Builder
        fun build(): AppComponent
    }

    fun inject(app: HeadlinesApp)
}