package band.effective.feed.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import band.effective.core.base.fragment.AppFragmentFactory
import band.effective.core.base.fragment.FragmentKey
import band.effective.feed.presentation.details.ArticleDetailsFragment
import band.effective.feed.presentation.feed.FeedFragment
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

    @Binds
    @IntoMap
    @FragmentKey(ArticleDetailsFragment::class)
    fun bindArticleFragment(fragment: ArticleDetailsFragment): Fragment
}