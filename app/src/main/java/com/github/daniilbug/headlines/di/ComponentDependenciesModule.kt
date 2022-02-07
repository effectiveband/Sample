package com.github.daniilbug.headlines.di

import com.github.daniilbug.about.di.AboutComponentDependencies
import com.github.daniilbug.core.di.ComponentDependencies
import com.github.daniilbug.core.di.ComponentDependenciesKey
import com.github.daniilbug.feed.di.FeedComponentDependencies
import com.github.daniilbug.main.di.MainComponentDependencies
import com.github.daniilbug.search.di.SearchComponentDependencies
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ComponentDependenciesModule {

    @Binds
    @IntoMap
    @ComponentDependenciesKey(MainComponentDependencies::class)
    fun bindMainComponentDependencies(dependencies: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(FeedComponentDependencies::class)
    fun bindFeedComponentDependencies(dependencies: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(AboutComponentDependencies::class)
    fun bindAboutComponentDependencies(dependencies: AppComponent): ComponentDependencies

    @Binds
    @IntoMap
    @ComponentDependenciesKey(SearchComponentDependencies::class)
    fun bindSearchComponentDependencies(dependencies: AppComponent): ComponentDependencies
}