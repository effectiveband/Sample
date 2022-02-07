package com.github.daniilbug.network.di

import com.github.daniilbug.core.di.InjectedKey
import com.github.daniilbug.network.news.EitherNewsAdapterFactory
import com.github.daniilbug.network.news.NewsApiKeyInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
interface NewsNetworkModule {

    @Module
    companion object {

        @NewsNetwork
        @Provides
        @Singleton
        fun provideNewsRetrofit(
            @BaseNetwork retrofit: Retrofit,
            @NewsNetwork newsClient: OkHttpClient,
            @Named(InjectedKey.News.BASE_URL) newsBaseUrl: String
        ): Retrofit {
            return retrofit.newBuilder()
                .baseUrl(newsBaseUrl)
                .client(newsClient)
                .addCallAdapterFactory(EitherNewsAdapterFactory())
                .build()
        }

        @NewsNetwork
        @Provides
        @Singleton
        fun provideNewsClient(
            @BaseNetwork client: OkHttpClient,
            @NewsNetwork interceptors: Set<@JvmSuppressWildcards Interceptor>
        ): OkHttpClient {
            return client.newBuilder()
                .apply { interceptors.forEach(::addInterceptor) }
                .build()
        }
    }

    @Binds
    @IntoSet
    @NewsNetwork
    fun bindNewsApiKeyInterceptor(interceptor: NewsApiKeyInterceptor): Interceptor
}