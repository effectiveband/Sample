package com.github.daniilbug.main

import androidx.lifecycle.ViewModel
import com.github.daniilbug.core.navigation.AppRouter
import com.github.daniilbug.mainNavigation.MainScreen
import javax.inject.Inject

class MainViewModel @Inject constructor(
    router: AppRouter<MainScreen>
): ViewModel() {

    init {
        router.newRoot(MainScreen.Feed)
    }
}