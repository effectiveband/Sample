package com.github.daniilbug.main.navigation

import com.github.daniilbug.core.base.fragment.createFragmentWithFactory
import com.github.daniilbug.core.navigation.cicerone.CiceroneScreenMapper
import com.github.daniilbug.core.navigation.key
import com.github.daniilbug.feed.presentation.FeedFragment
import com.github.daniilbug.mainNavigation.MainScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class MainNavigationMapper @Inject constructor(): CiceroneScreenMapper<MainScreen> {

    override fun map(screen: MainScreen): FragmentScreen {
        return when(screen) {
            MainScreen.Feed -> FragmentScreen(screen.key) { createFragmentWithFactory<FeedFragment>(it) }
            MainScreen.About -> TODO()
        }
    }

    override fun mapUri(uriString: String): List<MainScreen> {
        error("There is no deeplink for main flow")
    }
}