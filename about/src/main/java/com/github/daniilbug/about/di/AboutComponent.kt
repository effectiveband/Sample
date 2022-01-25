package com.github.daniilbug.about.di

import android.app.Activity
import androidx.fragment.app.FragmentFactory
import com.github.daniilbug.core.di.featureComponent
import com.github.daniilbug.core.di.findComponentDependencies
import dagger.Component

val aboutComponent = featureComponent<AboutComponent, Activity> { activity ->
    DaggerAboutComponent.builder()
        .aboutComponentDependencies(activity.findComponentDependencies())
        .build()
}

@Component(
    dependencies = [AboutComponentDependencies::class],
    modules = [AboutFragmentModule::class]
)
interface AboutComponent {
    fun getFragmentFactory(): FragmentFactory
}