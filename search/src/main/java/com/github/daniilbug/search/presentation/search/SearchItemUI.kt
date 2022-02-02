package com.github.daniilbug.search.presentation.search

import java.util.*

data class SearchItemUI(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?,
    val source: String,
    val date: Date
)