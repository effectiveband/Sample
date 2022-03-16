package band.effective.main.di.navigation

import band.effective.core.navigation.AppRouter
import band.effective.mainNavigation.MainScreen
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [MainNavigationModule::class]
)
interface MainNavigationComponent {

    fun getMainRouter(): AppRouter<MainScreen>

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance router: Router): MainNavigationComponent
    }
}