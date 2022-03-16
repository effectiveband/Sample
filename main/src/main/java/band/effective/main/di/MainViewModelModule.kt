package band.effective.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import band.effective.core.base.viewmodel.ViewModelKey
import band.effective.core.base.viewmodel.AppViewModelFactory
import band.effective.main.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
interface MainViewModelModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideViewModelFactory(
            creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
        ): ViewModelProvider.Factory {
            return AppViewModelFactory(creators)
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}