package band.effective.newsapi.di

import android.content.Context
import band.effective.network.di.NewsNetwork
import band.effective.newsapi.data.NewsRemoteDataSource
import band.effective.newsapi.data.NewsRestRemoteDataSource
import band.effective.newsapi.data.network.NewsApi
import band.effective.newsapi.domain.NewsRepository
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