package band.effective.feed.presentation.feed

import band.effective.newsapi.data.ArticleDomain

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