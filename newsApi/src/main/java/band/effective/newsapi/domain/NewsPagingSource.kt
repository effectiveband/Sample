package band.effective.newsapi.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import band.effective.core.entity.Either
import band.effective.core.entity.ErrorReason
import band.effective.core.entity.unpack
import band.effective.newsapi.data.ArticleDomain
import band.effective.newsapi.data.NewsDomain

class NewsLoadException(val reason: ErrorReason): Exception()

class NewsPagingSource(
    private val loadPage: suspend (page: Int, pageSize: Int) -> Either<ErrorReason, NewsDomain>
) : PagingSource<Int, ArticleDomain>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        val page = params.key ?: 1
        val pageSize = params.loadSize
        return loadPage(page, pageSize).unpack(
            success = { news ->
                LoadResult.Page(
                    data = news.articles,
                    prevKey = (page - 1).takeIf { it >= 0 },
                    nextKey = (page + 1).takeIf { news.articles.size == pageSize },
                    itemsAfter = news.total - (page - 1) * pageSize - news.articles.size
                )
            },
            error = { reason ->
                LoadResult.Error(NewsLoadException(reason))
            }
        )
    }
}