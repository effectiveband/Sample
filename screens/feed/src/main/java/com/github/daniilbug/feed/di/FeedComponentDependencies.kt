package com.github.daniilbug.feed.di

import com.github.daniilbug.core.di.CommonDependencies
import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.core.navigation.flow.FlowCoordinator
import com.github.daniilbug.mainNavigation.MainScreen
import com.github.daniilbug.newsapi.domain.NewsRepository

interface FeedComponentDependencies: CommonDependencies {
    fun getMainRouter(): AppRouter<MainScreen>
    fun getFlowCoordinator(): FlowCoordinator
    fun getNewsRepository(): NewsRepository
}