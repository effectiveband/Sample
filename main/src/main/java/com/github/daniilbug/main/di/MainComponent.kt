package com.github.daniilbug.main.di

import android.app.Activity
import com.github.daniilbug.core.di.featureComponent
import com.github.daniilbug.core.di.findComponentDependencies
import com.github.daniilbug.main.MainActivity
import dagger.Component

val mainComponent = featureComponent<MainComponent, Activity> { activity ->
    DaggerMainComponent.builder()
        .mainComponentDependencies(activity.findComponentDependencies())
        .build()
}

@Component(
    dependencies = [MainComponentDependencies::class],
    modules = [MainViewModelModule::class]
)
interface MainComponent {
    fun inject(activity: MainActivity)
}