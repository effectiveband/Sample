package band.effective.headlines.di

import android.content.Context
import band.effective.about.di.AboutComponentDependencies
import band.effective.debug.di.DebugMenuModule
import band.effective.feed.di.FeedComponentDependencies
import band.effective.headlines.HeadlinesApp
import band.effective.main.di.MainComponentDependencies
import band.effective.main.di.navigation.MainNavigationComponent
import band.effective.network.di.NetworkModule
import band.effective.newsapi.di.NewsModule
import band.effective.search.di.SearchComponentDependencies
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppNavigationModule::class,
        ComponentDependenciesModule::class,
        DebugMenuModule::class,
        NetworkModule::class,
        NewsModule::class
    ],
    dependencies = [MainNavigationComponent::class]
)
interface AppComponent :
    MainComponentDependencies,
    FeedComponentDependencies,
    AboutComponentDependencies,
    SearchComponentDependencies {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun cicerone(cicerone: Cicerone<Router>): Builder

        @BindsInstance
        fun applicationContext(context: Context): Builder

        fun mainNavigationComponent(mainNavigationComponent: MainNavigationComponent): Builder
        fun build(): AppComponent
    }

    fun inject(app: HeadlinesApp)
}