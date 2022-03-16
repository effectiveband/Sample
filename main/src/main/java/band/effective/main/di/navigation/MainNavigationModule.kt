package band.effective.main.di.navigation

import band.effective.core.navigation.AppRouter
import band.effective.core.navigation.cicerone.CiceroneAppRouter
import band.effective.core.navigation.cicerone.CiceroneScreenMapper
import band.effective.main.navigation.MainNavigationMapper
import band.effective.mainNavigation.MainScreen
import com.github.terrakok.cicerone.Router
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
interface MainNavigationModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Reusable
        fun provideRouter(
            ciceroneRouter: Router,
            mainMapper: CiceroneScreenMapper<MainScreen>
        ): AppRouter<MainScreen> = CiceroneAppRouter(ciceroneRouter, mainMapper)
    }

    @Binds
    fun bindMainMapper(mapper: MainNavigationMapper): CiceroneScreenMapper<MainScreen>
}