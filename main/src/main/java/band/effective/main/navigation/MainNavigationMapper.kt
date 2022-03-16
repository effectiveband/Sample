package band.effective.main.navigation

import band.effective.about.presentation.AboutFragment
import band.effective.core.base.fragment.createFragmentWithFactory
import band.effective.core.navigation.cicerone.CiceroneScreenMapper
import band.effective.core.navigation.key
import band.effective.feed.presentation.details.ArticleDetailsFragment
import band.effective.feed.presentation.feed.FeedFragment
import band.effective.mainNavigation.MainScreen
import band.effective.search.presentation.search.SearchFragment
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