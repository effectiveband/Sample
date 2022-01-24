package com.github.daniilbug.feed.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.daniilbug.core.base.fragment.AppFragmentFactory
import com.github.daniilbug.core.base.fragment.FragmentKey
import com.github.daniilbug.feed.presentation.FeedFragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
interface FeedFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideFragmentFactory(
            creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
        ): FragmentFactory {
            return AppFragmentFactory(creators)
        }
    }

    @Binds
    @IntoMap
    @FragmentKey(FeedFragment::class)
    fun bindFeedFragment(fragment: FeedFragment): Fragment
}