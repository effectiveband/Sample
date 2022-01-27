package com.github.daniilbug.search.presentation.search

sealed class SearchEvent {
    class Search(val query: String): SearchEvent()
}