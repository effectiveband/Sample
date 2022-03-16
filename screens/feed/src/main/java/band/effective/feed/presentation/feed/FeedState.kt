package band.effective.feed.presentation.feed

sealed class FeedState {
    object Loading: FeedState()
    object NetworkError: FeedState()
    data class UnexpectedError(val message: String): FeedState()
    data class Headlines(val items: List<HeadlinesItemUI>): FeedState()
}