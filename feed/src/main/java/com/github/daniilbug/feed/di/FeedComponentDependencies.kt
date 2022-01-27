package com.github.daniilbug.feed.di

import com.github.daniilbug.core.di.CommonDependencies
import com.github.daniilbug.newsapi.domain.NewsRepository

interface FeedComponentDependencies: CommonDependencies {
    fun getNewsRepository(): NewsRepository
}