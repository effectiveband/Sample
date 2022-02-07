package com.github.daniilbug.newsapi.di

import android.content.Context
import com.github.daniilbug.network.di.NewsNetwork
import com.github.daniilbug.newsapi.data.NewsRemoteDataSource
import com.github.daniilbug.newsapi.data.NewsRestRemoteDataSource
import com.github.daniilbug.newsapi.data.network.NewsApi
import com.github.daniilbug.newsapi.domain.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
interface NewsModule {

    @Module
    companion object {

        @Provides
        fun provideApi(
            @NewsNetwork retrofit: Retrofit
        ): NewsApi = retrofit.create()

        @Provides
        @Singleton
        fun provideNewsRepository(
            context: Context,
            remoteDataSource: NewsRemoteDataSource
        ): NewsRepository = NewsRepository(context, remoteDataSource)
    }

    @Binds
    @Singleton
    fun bindNewsRemoteDataSource(remoteDataSource: NewsRestRemoteDataSource): NewsRemoteDataSource
}