package com.github.daniilbug.search.di

import com.github.daniilbug.core.di.CommonDependencies
import com.github.daniilbug.newsapi.domain.NewsRepository

interface SearchComponentDependencies: CommonDependencies {
    fun getNewsRepository(): NewsRepository
}