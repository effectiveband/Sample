package com.github.daniilbug.feed.presentation.feed

sealed class FeedEvent {
    class OpenDetails(val headlinesItem: HeadlinesItemUI): FeedEvent()
}