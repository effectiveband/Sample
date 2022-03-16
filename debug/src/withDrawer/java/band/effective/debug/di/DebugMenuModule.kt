package band.effective.debug.di

import android.content.Context
import au.com.gridstone.debugdrawer.okhttplogs.HttpLogger
import band.effective.core.base.RootViewBinder
import band.effective.core.di.InjectedKey
import band.effective.debug.BuildConfig
import band.effective.network.di.BaseNetwork
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
        rootViewBinder: DebugDrawerRootViewBinder
    ): RootViewBinder
}