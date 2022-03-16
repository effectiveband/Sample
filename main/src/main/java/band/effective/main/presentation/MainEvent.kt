package band.effective.main.presentation

sealed class MainEvent {
    object OpenFeed: MainEvent()
    object OpenAbout: MainEvent()
    object OpenSearch: MainEvent()
}