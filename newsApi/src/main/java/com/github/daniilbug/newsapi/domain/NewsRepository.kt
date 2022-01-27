package com.github.daniilbug.newsapi.domain

import android.content.Context
import android.os.Build
import androidx.paging.PagingSource
import com.github.daniilbug.newsapi.data.ArticleDomain
import com.github.daniilbug.newsapi.data.NewsRemoteDataSource

class NewsRepository(
    context: Context,
    private val remoteDataSource: NewsRemoteDataSource
) {

    private val country = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0].country
    } else {
        context.resources.configuration.locale.country
    }

    fun getHeadlines(): PagingSource<Int, ArticleDomain> {
        return NewsPagingSource { page, pageSize ->
            remoteDataSource.getHeadlinesPage(country, page, pageSize)
        }
    }

    fun getArticles(searchQuery: String): PagingSource<Int, ArticleDomain> {
        return NewsPagingSource { page, pageSize ->
            remoteDataSource.getEverythingPage(searchQuery, page, pageSize)
        }
    }
}