package com.github.daniilbug.newsapi.data

data class NewsDomain(
    val total: Int,
    val articles: List<ArticleDomain>
)