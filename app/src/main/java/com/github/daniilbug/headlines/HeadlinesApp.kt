package com.github.daniilbug.headlines

import android.app.Application
import band.effective.core.di.HasComponentDependencies
import com.github.daniilbug.core.di.ComponentDependenciesProvider
import com.github.daniilbug.headlines.di.AppComponent
import com.github.daniilbug.headlines.di.DaggerAppComponent
import com.github.daniilbug.main.di.navigation.DaggerMainNavigationComponent
import com.github.terrakok.cicerone.Cicerone
import com.google.android.material.color.DynamicColors
import javax.inject.Inject

class HeadlinesApp: Application(), HasComponentDependencies {

    @Inject
    override lateinit var dependencies: ComponentDependenciesProvider

    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this, R.style.Theme_Headlines)

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