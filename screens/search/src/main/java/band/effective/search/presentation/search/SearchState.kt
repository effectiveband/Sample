package band.effective.search.presentation.search

import androidx.paging.PagingData

sealed class SearchState {
    object Empty: SearchState()
    data class News(val news: PagingData<SearchItemUI>) : SearchState()
}