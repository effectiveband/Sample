package com.github.daniilbug.feed.presentation.feed

import com.github.daniilbug.newsapi.data.ArticleDomain

fun ArticleDomain.asHeadlinesItemUI(): HeadlinesItemUI {
    return HeadlinesItemUI(
        title,
        content,
        imageUrl,
        url,
        source,
        date
    )
}