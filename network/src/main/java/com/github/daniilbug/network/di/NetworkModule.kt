package com.github.daniilbug.network.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.addAdapter
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    includes = [NewsNetworkModule::class]
)
object NetworkModule {

    @BaseNetwork
    @Provides
    @Singleton
    fun provideBaseOkHttpClient(
        @BaseNetwork interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply { interceptors.forEach(::addInterceptor) }
            .callTimeout(5, TimeUnit.SECONDS)
            .build()
    }

    @BaseNetwork
    @Provides
    @Singleton
    fun provideBaseRetrofit(
        @BaseNetwork client: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://base.url")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addAdapter(Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }
}