package com.github.daniilbug.main.navigation

import com.github.daniilbug.about.presentation.AboutFragment
import com.github.daniilbug.core.base.fragment.createFragmentWithFactory
import com.github.daniilbug.core.navigation.cicerone.CiceroneScreenMapper
import com.github.daniilbug.core.navigation.key
import com.github.daniilbug.feed.presentation.details.ArticleDetailsFragment
import com.github.daniilbug.feed.presentation.feed.FeedFragment
import com.github.daniilbug.mainNavigation.MainScreen
import com.github.daniilbug.search.presentation.search.SearchFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class MainNavigationMapper @Inject constructor(): CiceroneScreenMapper<MainScreen> {

    override fun map(screen: MainScreen): FragmentScreen {
        return when(screen) {
            MainScreen.Feed -> FragmentScreen(screen.key) { createFragmentWithFactory<FeedFragment>(it) }
            MainScreen.Search -> FragmentScreen(screen.key) { createFragmentWithFactory<SearchFragment>(it) }
            MainScreen.About -> FragmentScreen(screen.key) { createFragmentWithFactory<AboutFragment>(it) }
            is MainScreen.Article -> FragmentScreen(screen.key) {
                ArticleDetailsFragment.withDetails(it, screen.details)
            }
        }
    }

    override fun mapUri(uriString: String): List<MainScreen> {
        error("There is no deeplink for main flow")
    }
}