package band.effective.feed.presentation.feed

sealed class FeedEvent {
    class OpenDetails(val headlinesItem: HeadlinesItemUI): FeedEvent()
}