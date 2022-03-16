package band.effective.newsapi.data

import band.effective.core.entity.Either
import band.effective.core.entity.ErrorReason

interface NewsRemoteDataSource {
    suspend fun getHeadlinesPage(
        country: String,
        page: Int,
        pageSize: Int
    ): Either<ErrorReason, NewsDomain>

    suspend fun getEverythingPage(
        query: String,
        page: Int,
        pageSize: Int
    ): Either<ErrorReason, NewsDomain>
}