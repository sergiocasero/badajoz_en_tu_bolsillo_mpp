package com.badajoz.badajozentubolsillo.model

sealed class AppError : Throwable() {
    object Unknown : AppError()
    object NotFound : AppError()

    object LocalError : AppError()

    sealed class Poi : AppError() {
        object InvalidId : Poi()
        object InvalidCoordinates : Poi()
        object EmptyList : Poi()
    }
}