package com.github.daniilbug.newsapi.data

import java.util.*

data class ArticleDomain(
    val title: String,
    val description: String,
    val source: String,
    val url: String,
    val imageUrl: String,
    val date: Date
)