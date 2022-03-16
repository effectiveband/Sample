package band.effective.main.di

import android.app.Activity
import band.effective.core.di.featureComponent
import band.effective.core.di.findComponentDependencies
import band.effective.main.presentation.MainActivity
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