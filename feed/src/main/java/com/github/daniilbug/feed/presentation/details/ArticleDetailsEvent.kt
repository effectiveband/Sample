package com.github.daniilbug.feed.presentation.details

sealed class ArticleDetailsEvent {
    object OpenUrl: ArticleDetailsEvent()
}