package com.github.daniilbug.search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.mainNavigation.MainScreen
import com.github.daniilbug.newsapi.data.ArticleDomain
import com.github.daniilbug.newsapi.domain.NewsRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val router: AppRouter<MainScreen>,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val mutableQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    val state: StateFlow<SearchState> = mutableQuery.debounce(500L).flatMapLatest { query ->
        when {
            query.isEmpty() -> flowOf(SearchState.Empty)
            else -> {
                Pager(
                    config = PagingConfig(pageSize = 10, enablePlaceholders = true),
                    pagingSourceFactory = { newsRepository.getArticles(query) }
                ).flow.cachedIn(viewModelScope).mapLatest { data ->
                    SearchState.News(
                        data.map(ArticleDomain::asSearchItemUI)
                    )
                }
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, SearchState.Empty)

    fun sendEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> mutableQuery.value = event.query
            is SearchEvent.OpenDetails -> openDetails(event.item)
        }
    }

    private fun openDetails(item: SearchItemUI) {
        router.open(MainScreen.Article(item.asArticleDetails()))
    }
}