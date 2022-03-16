package com.github.daniilbug.debug.di

import android.content.Context
import au.com.gridstone.debugdrawer.okhttplogs.HttpLogger
import com.github.daniilbug.core.base.RootViewBinder
import com.github.daniilbug.core.di.InjectedKey
import com.github.daniilbug.debug.BuildConfig
import com.github.daniilbug.network.di.BaseNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
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

        @Provides
        fun provideHttpLogger(context: Context): HttpLogger {
            return HttpLogger(context, prettyPrintJson = true)
        }

        @Provides
        @BaseNetwork
        @IntoSet
        fun provideLoggingInterceptor(logger: HttpLogger): Interceptor {
            return logger.interceptor
        }
    }

    @Binds
    @Singleton
    fun bindRootViewBinder(
        rootViewBinder: NoDebugDrawerRootViewBinder
    ): RootViewBinder
}
