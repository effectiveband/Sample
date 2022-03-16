package band.effective.search.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import band.effective.core.base.fragment.AppFragmentFactory
import band.effective.core.base.fragment.FragmentKey
import band.effective.search.presentation.search.SearchFragment
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