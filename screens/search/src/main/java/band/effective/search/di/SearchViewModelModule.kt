package band.effective.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import band.effective.core.base.viewmodel.ViewModelKey
import band.effective.core.base.viewmodel.AppViewModelFactory
import band.effective.search.presentation.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
interface SearchViewModelModule {

    @Module
    companion object {

        @Provides
        fun provideViewModelFactory(
            creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory {
            return AppViewModelFactory(creators)
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}