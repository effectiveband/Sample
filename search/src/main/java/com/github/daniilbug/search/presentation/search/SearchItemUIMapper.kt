package com.github.daniilbug.search.presentation.search

import com.github.daniilbug.newsapi.data.ArticleDomain

fun ArticleDomain.asSearchItemUI(): SearchItemUI {
    return SearchItemUI(
        title = title,
        description = description,
        source = source,
        url = url,
        imageUrl = imageUrl,
        date = date,
        content = content
    )
}