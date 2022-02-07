package com.github.daniilbug.search.presentation.search

import java.util.*
import com.github.daniilbug.mainNavigation.ArticleDetails as ArticleDetails1

data class SearchItemUI(
    val title: String,
    val description: String,
    val source: String,
    val url: String,
    val imageUrl: String?,
    val date: Date,
    val content: String
)

fun SearchItemUI.asArticleDetails(): ArticleDetails1 {
    return ArticleDetails1(
        title = title,
        content = content,
        imageUrl = imageUrl,
        url = url,
        source = source,
        date = date
    )
}