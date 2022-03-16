package band.effective.feed.presentation.details

sealed class ArticleDetailsEvent {
    object OpenUrl: ArticleDetailsEvent()
}