package band.effective.core.navigation.cicerone

import band.effective.core.base.activity.NavigationActivity
import band.effective.core.navigation.NavigationBinder
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