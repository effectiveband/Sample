package com.github.daniilbug.search.presentation.search

import androidx.paging.PagingData

sealed class SearchState {
    object Loading : SearchState()
    object Empty: SearchState()
    data class News(val news: PagingData<SearchItemUI>) : SearchState()
}