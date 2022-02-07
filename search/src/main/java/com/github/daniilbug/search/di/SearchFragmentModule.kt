package com.github.daniilbug.search.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.daniilbug.core.base.fragment.AppFragmentFactory
import com.github.daniilbug.core.base.fragment.FragmentKey
import com.github.daniilbug.search.presentation.search.SearchFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
interface SearchFragmentModule {

    @Module
    companion object {

        @Provides
        fun provideFragmentFactory(
            creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
        ): FragmentFactory {
            return AppFragmentFactory(creators)
        }
    }

    @Binds
    @IntoMap
    @FragmentKey(SearchFragment::class)
    fun bindSearchFragment(fragment: SearchFragment): Fragment
}