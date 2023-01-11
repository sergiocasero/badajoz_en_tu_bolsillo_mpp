package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import io.ktor.client.plugins.ResponseException

interface NetworkDataSource


internal suspend fun <R> NetworkDataSource.execute(block: suspend () -> R): Either<AppError, R> = try {
    Either.Right(block())
} catch (t: Throwable) {
    t.printStackTrace()
    Either.Left(
        when (t) {
            is AppError -> t
            is ResponseException -> {
                when (t.response.status.value) {
                    404 -> AppError.NotFound
                    500 -> AppError.ServerError
                    else -> AppError.Unknown
                }
            }

            else -> AppError.NoInternet
        }
    )
}