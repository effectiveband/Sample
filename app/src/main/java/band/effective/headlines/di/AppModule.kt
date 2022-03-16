package band.effective.headlines.di

import band.effective.core.di.InjectedKey
import band.effective.core.navigation.flow.FlowBinder
import band.effective.core.navigation.flow.FlowCoordinator
import band.effective.headlines.navigation.ActivityFlowCoordinator
import band.effective.headlines.navigation.NavigationFlowMapper
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Named

@Module
object AppModule {

    @JvmStatic
    @Provides
    @Named(InjectedKey.Configuration.VERSION_NAME)
    fun provideAppVersionName(): String = band.effective.headlines.BuildConfig.VERSION_NAME

    @JvmStatic
    @Provides
    fun provideScreenMapper(): NavigationFlowMapper = NavigationFlowMapper()

    @JvmStatic
    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.getNavigatorHolder()

    @JvmStatic
    @Provides
    @Reusable
    fun provideFlowBinder(coordinator: ActivityFlowCoordinator): FlowBinder = coordinator.Binder()

    @JvmStatic
    @Provides
    fun provideFlowCoordinator(coordinator: ActivityFlowCoordinator): FlowCoordinator = coordinator
}