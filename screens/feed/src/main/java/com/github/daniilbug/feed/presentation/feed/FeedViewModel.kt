package com.github.daniilbug.feed.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.mainNavigation.MainScreen
import com.github.daniilbug.newsapi.data.ArticleDomain
import com.github.daniilbug.newsapi.domain.NewsRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val router: AppRouter<MainScreen>,
    private val newsRepository: NewsRepository
) : ViewModel() {

    val headlines = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = true),
        pagingSourceFactory = { newsRepository.getHeadlines() }
    ).flow.cachedIn(viewModelScope).mapLatest { data ->
        data.map(ArticleDomain::asHeadlinesItemUI)
    }

    fun sendEvent(event: FeedEvent) {
        when (event) {
            is FeedEvent.OpenDetails -> openDetails(event.headlinesItem)
        }
    }

    private fun openDetails(headlinesItem: HeadlinesItemUI) {
        router.open(
            MainScreen.Article(headlinesItem.asArticleDetails())
        )
    }
}