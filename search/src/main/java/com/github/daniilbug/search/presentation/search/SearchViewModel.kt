package com.github.daniilbug.search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.github.daniilbug.newsapi.data.ArticleDomain
import com.github.daniilbug.newsapi.domain.NewsRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val mutableQuery = MutableStateFlow("")

    val state: StateFlow<SearchState> = mutableQuery.flatMapLatest { query ->
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = true),
            pagingSourceFactory = { newsRepository.getArticles(query) }
        ).flow.cachedIn(viewModelScope).mapLatest { data ->
            SearchState.News(
                data.map(ArticleDomain::asSearchItemUI)
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, SearchState.Loading)

    fun sendEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Search -> mutableQuery.value = event.query
        }
    }
}