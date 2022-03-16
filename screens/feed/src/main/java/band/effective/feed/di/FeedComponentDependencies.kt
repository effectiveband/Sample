package band.effective.feed.di

import band.effective.core.di.CommonDependencies
import band.effective.core.navigation.AppRouter
import band.effective.core.navigation.flow.FlowCoordinator
import band.effective.mainNavigation.MainScreen
import band.effective.newsapi.domain.NewsRepository

interface FeedComponentDependencies: CommonDependencies {
    fun getMainRouter(): AppRouter<MainScreen>
    fun getFlowCoordinator(): FlowCoordinator
    fun getNewsRepository(): NewsRepository
}