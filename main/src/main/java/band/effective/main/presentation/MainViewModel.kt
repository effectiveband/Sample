package band.effective.main.presentation

import androidx.lifecycle.ViewModel
import band.effective.core.navigation.AppRouter
import band.effective.mainNavigation.MainScreen
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: AppRouter<MainScreen>
) : ViewModel() {

    init {
        router.newRoot(MainScreen.Feed)
    }

    fun sendEvent(event: MainEvent) {
        when (event) {
            MainEvent.OpenFeed -> router.newRoot(MainScreen.Feed)
            MainEvent.OpenSearch -> router.newRoot(MainScreen.Search)
            MainEvent.OpenAbout -> router.newRoot(MainScreen.About)
        }
    }
}