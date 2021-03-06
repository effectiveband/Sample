package band.effective.mainNavigation

import band.effective.core.navigation.AppScreen

sealed class MainScreen : AppScreen {
    object Feed : MainScreen() {
        override val name: String
            get() = "Feed"
    }

    object About : MainScreen() {
        override val name: String
            get() = "About"
    }

    object Search: MainScreen() {
        override val name: String
            get() = "Search"
    }

    data class Article(val details: ArticleDetails): MainScreen() {
        override val name: String
            get() = "Article"
    }
}