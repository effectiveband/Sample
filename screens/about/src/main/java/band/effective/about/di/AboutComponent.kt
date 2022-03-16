package band.effective.about.di

import android.app.Activity
import androidx.fragment.app.FragmentFactory
import band.effective.core.di.featureComponent
import band.effective.core.di.findComponentDependencies
import dagger.Component

val aboutComponent = featureComponent<AboutComponent, Activity> { activity ->
    DaggerAboutComponent.builder()
        .aboutComponentDependencies(activity.findComponentDependencies())
        .build()
}

@Component(
    dependencies = [AboutComponentDependencies::class],
    modules = [
        AboutFragmentModule::class,
        AboutViewModelModule::class
    ]
)
interface AboutComponent {
    fun getFragmentFactory(): FragmentFactory
}