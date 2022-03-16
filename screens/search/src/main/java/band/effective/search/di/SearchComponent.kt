package band.effective.search.di

import android.app.Activity
import androidx.fragment.app.FragmentFactory
import band.effective.core.di.featureComponent
import band.effective.core.di.findComponentDependencies
import dagger.Component

val searchComponent = featureComponent<SearchComponent, Activity> { activity ->
    band.effective.search.di.DaggerSearchComponent.builder()
        .searchComponentDependencies(activity.findComponentDependencies())
        .build()
}

@Component(
    dependencies = [SearchComponentDependencies::class],
    modules = [
        SearchFragmentModule::class,
        SearchViewModelModule::class
    ]
)
interface SearchComponent {
    fun getFragmentFactory(): FragmentFactory
}