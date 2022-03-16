package band.effective.newsapi.data

import band.effective.core.entity.Either
import band.effective.core.entity.ErrorReason
import band.effective.core.entity.map
import band.effective.newsapi.data.network.ArticleNetwork
import band.effective.newsapi.data.network.NewsApi
import band.effective.newsapi.data.network.asDomain
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