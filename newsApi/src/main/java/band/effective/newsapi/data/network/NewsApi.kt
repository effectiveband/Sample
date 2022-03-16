package band.effective.newsapi.data.network

import band.effective.core.entity.Either
import band.effective.core.entity.ErrorReason
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Either<ErrorReason, NewsPageNetwork>

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("sortBy") sortBy: String = "popularity",
    ): Either<ErrorReason, NewsPageNetwork>
}