package band.effective.search.presentation.search

import band.effective.newsapi.data.ArticleDomain

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