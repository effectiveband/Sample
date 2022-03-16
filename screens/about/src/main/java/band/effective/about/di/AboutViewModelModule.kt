package band.effective.about.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import band.effective.core.base.viewmodel.ViewModelKey
import band.effective.about.presentation.AboutViewModel
import band.effective.core.base.viewmodel.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module
interface AboutViewModelModule {

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
    @ViewModelKey(AboutViewModel::class)
    fun bindAboutViewModel(viewModel: AboutViewModel): ViewModel
}