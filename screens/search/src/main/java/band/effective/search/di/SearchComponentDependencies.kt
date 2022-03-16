package band.effective.search.di

import band.effective.core.di.CommonDependencies
import band.effective.core.navigation.AppRouter
import band.effective.mainNavigation.MainScreen
import band.effective.newsapi.domain.NewsRepository

interface SearchComponentDependencies: CommonDependencies {
    fun getMainRouter(): AppRouter<MainScreen>
    fun getNewsRepository(): NewsRepository
}