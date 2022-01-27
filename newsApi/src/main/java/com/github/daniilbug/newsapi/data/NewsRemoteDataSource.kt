package com.github.daniilbug.newsapi.data

import com.github.daniilbug.core.entity.Either
import com.github.daniilbug.core.entity.ErrorReason

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