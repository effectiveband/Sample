package band.effective.newsapi.data.network

import band.effective.newsapi.data.ArticleDomain

fun ArticleNetwork.asDomain(): ArticleDomain {
    return ArticleDomain(
        title = title,
        description = description.orEmpty(),
        source = source.name,
        url = url,
        imageUrl = imageUrl,
        date = date,
        content = content.orEmpty()
    )
}