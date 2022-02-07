package com.github.daniilbug.search.di

import com.github.daniilbug.core.di.CommonDependencies
import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.mainNavigation.MainScreen
import com.github.daniilbug.newsapi.domain.NewsRepository

interface SearchComponentDependencies: CommonDependencies {
    fun getMainRouter(): AppRouter<MainScreen>
    fun getNewsRepository(): NewsRepository
}