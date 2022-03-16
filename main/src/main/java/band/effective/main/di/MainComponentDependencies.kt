package band.effective.main.di

import band.effective.core.base.RootViewBinder
import band.effective.core.di.CommonDependencies
import band.effective.core.navigation.AppRouter
import band.effective.core.navigation.NavigationBinder
import band.effective.core.navigation.flow.FlowBinder
import band.effective.mainNavigation.MainScreen

interface MainComponentDependencies : CommonDependencies {
    fun getRootViewBinder(): RootViewBinder
    fun getFlowBinder(): FlowBinder
    fun getNavigationBinder(): NavigationBinder
    fun getMainRouter(): AppRouter<MainScreen>
}