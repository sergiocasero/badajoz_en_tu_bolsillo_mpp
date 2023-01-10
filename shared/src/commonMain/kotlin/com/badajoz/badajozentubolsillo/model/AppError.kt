package com.badajoz.badajozentubolsillo.model

sealed class AppError : Throwable() {
    object Unknown : AppError()
    object ServerError : AppError()
    object NotFound : AppError()
    object LocalError : AppError()
    object NoInternet : AppError()

}