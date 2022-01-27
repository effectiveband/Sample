package com.github.daniilbug.search.presentation.search

import com.github.daniilbug.newsapi.data.ArticleDomain

fun ArticleDomain.asSearchItemUI(): SearchItemUI {
    return SearchItemUI(
        id = title,
        title = title,
        description = description,
        imageUrl = imageUrl,
        source = source,
        date = date
    )
}