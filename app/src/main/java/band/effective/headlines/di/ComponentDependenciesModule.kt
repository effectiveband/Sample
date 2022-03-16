package band.effective.headlines.di

import band.effective.about.di.AboutComponentDependencies
import band.effective.core.di.ComponentDependencies
import band.effective.core.di.ComponentDependenciesKey
import band.effective.feed.di.FeedComponentDependencies
import band.effective.main.di.MainComponentDependencies
import band.effective.search.di.SearchComponentDependencies
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