package com.github.daniilbug.newsapi.data.network

import com.github.daniilbug.newsapi.data.ArticleDomain

fun ArticleNetwork.asDomain(): ArticleDomain {
    return ArticleDomain(
        title = title,
        description = description.orEmpty(),
        source = source.name,
        url = url,
        imageUrl = imageUrl,
        date = date
    )
}