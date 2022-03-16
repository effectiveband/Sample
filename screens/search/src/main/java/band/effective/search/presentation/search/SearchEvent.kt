package band.effective.search.presentation.search

sealed class SearchEvent {
    class Search(val query: String): SearchEvent()
    class OpenDetails(val item: SearchItemUI): SearchEvent()
}