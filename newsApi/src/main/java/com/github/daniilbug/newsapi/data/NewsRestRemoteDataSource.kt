package com.github.daniilbug.newsapi.data

import com.github.daniilbug.core.entity.Either
import com.github.daniilbug.core.entity.ErrorReason
import com.github.daniilbug.core.entity.map
import com.github.daniilbug.newsapi.data.network.ArticleNetwork
import com.github.daniilbug.newsapi.data.network.NewsApi
import com.github.daniilbug.newsapi.data.network.asDomain
import javax.inject.Inject

class NewsRestRemoteDataSource @Inject constructor(
    private val newsApi: NewsApi
): NewsRemoteDataSource {

    override suspend fun getHeadlinesPage(
        country: String,
        page: Int,
        pageSize: Int
    ): Either<ErrorReason, NewsDomain> {
        return newsApi.getHeadlines(country, page, pageSize).map { news ->
            NewsDomain(news.total, news.articles.map(ArticleNetwork::asDomain))
        }
    }

    override suspend fun getEverythingPage(
        query: String,
        page: Int,
        pageSize: Int
    ): Either<ErrorReason, NewsDomain> {
        return newsApi.getEverything(query, page, pageSize).map { news ->
            NewsDomain(news.total, news.articles.map(ArticleNetwork::asDomain))
        }
    }
}