package band.effective.feed.presentation.details

import java.util.*

sealed class ArticleDetailsState {
    object Loading: ArticleDetailsState()

    data class Article(
        val title: String,
        val imageUrl: String?,
        val url: String,
        val date: Date,
        val source: String,
        val content: String
    ): ArticleDetailsState()
}