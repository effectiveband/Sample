package com.github.daniilbug.search.di

import android.app.Activity
import androidx.fragment.app.FragmentFactory
import com.github.daniilbug.core.di.featureComponent
import com.github.daniilbug.core.di.findComponentDependencies
import dagger.Component

val searchComponent = featureComponent<SearchComponent, Activity> { activity ->
    DaggerSearchComponent.builder()
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