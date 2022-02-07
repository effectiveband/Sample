package com.github.daniilbug.core.navigation.cicerone

import com.github.daniilbug.core.base.activity.NavigationActivity
import com.github.daniilbug.core.navigation.NavigationBinder
import com.github.terrakok.cicerone.NavigatorHolder

class CiceroneNavigationBinder(
    private val navigatorHolder: NavigatorHolder
) : NavigationBinder {

    override fun bind(navigationActivity: NavigationActivity) {
        navigatorHolder.setNavigator(
            CustomCiceroneNavigator(navigationActivity)
        )
    }

    override fun unbind() {
        navigatorHolder.removeNavigator()
    }
}