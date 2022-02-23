package com.github.daniilbug.about.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.daniilbug.about.presentation.AboutFragment
import com.github.daniilbug.core.base.fragment.AppFragmentFactory
import com.github.daniilbug.core.base.fragment.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
interface AboutFragmentModule {

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
    @FragmentKey(AboutFragment::class)
    fun bindAboutFragment(fragment: AboutFragment): Fragment
}