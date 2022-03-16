package band.effective.core.navigation

import band.effective.core.base.activity.NavigationActivity

interface NavigationBinder {
    fun bind(navigationActivity: NavigationActivity)
    fun unbind()
}
