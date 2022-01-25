package com.github.daniilbug.main.presentation

import androidx.lifecycle.ViewModel
import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.mainNavigation.MainScreen
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: AppRouter<MainScreen>
) : ViewModel() {

    init {
        router.newRoot(MainScreen.Feed)
    }

    fun sendEvent(event: MainEvent) {
        when (event) {
            MainEvent.OpenAbout -> router.newRoot(MainScreen.About)
            MainEvent.OpenFeed -> router.newRoot(MainScreen.Feed)
        }
    }
}