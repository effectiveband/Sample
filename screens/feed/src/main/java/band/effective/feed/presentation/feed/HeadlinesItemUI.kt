package band.effective.feed.presentation.feed

import band.effective.mainNavigation.ArticleDetails
import java.util.*

data class HeadlinesItemUI(
    val title: String,
    val content: String,
    val imageUrl: String?,
    val url: String,
    val source: String,
    val date: Date
)

fun HeadlinesItemUI.asArticleDetails(): ArticleDetails {
    return ArticleDetails(
        title,
        content,
        imageUrl,
        url,
        source,
        date
    )
}