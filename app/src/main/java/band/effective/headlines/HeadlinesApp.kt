package band.effective.headlines

import android.app.Application
import band.effective.core.di.HasComponentDependencies
import band.effective.core.di.ComponentDependenciesProvider
import band.effective.headlines.di.DaggerAppComponent
import band.effective.main.di.navigation.DaggerMainNavigationComponent
import com.github.terrakok.cicerone.Cicerone
import com.google.android.material.color.DynamicColors
import timber.log.Timber
import javax.inject.Inject

class HeadlinesApp: Application(), HasComponentDependencies {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        DynamicColors.applyToActivitiesIfAvailable(this)

        val cicerone = Cicerone.create()

        DaggerAppComponent.builder()
            .applicationContext(this)
            .cicerone(cicerone)
            .mainNavigationComponent(
                DaggerMainNavigationComponent.factory().create(cicerone.router)
            )
            .build()
            .inject(this)
    }
}