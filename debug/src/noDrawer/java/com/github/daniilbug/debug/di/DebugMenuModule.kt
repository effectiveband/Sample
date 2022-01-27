package com.github.daniilbug.debug.di

import com.github.daniilbug.core.base.RootViewBinder
import com.github.daniilbug.core.di.InjectedKey
import com.github.daniilbug.debug.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
interface DebugMenuModule {

    @Module
    companion object {

        @Provides
        @Named(InjectedKey.News.BASE_URL)
        fun provideNewsBaseUrl(): String = BuildConfig.NEWS_BASE_URL

        @Provides
        @Named(InjectedKey.News.API_KEY)
        fun provideNewsApiKey(): String = BuildConfig.NEWS_API_KEY
    }

    @Binds
    @Singleton
    fun bindRootViewBinder(
        rootViewBinder: NoDebugDrawerRootViewBinder
    ): RootViewBinder
}