package com.badajoz.badajozentubolsillo.model

sealed class Error : Throwable() {
    object Unknown : Error()
    object NotFound : Error()

    sealed class Poi : Error() {
        object InvalidId : Poi()
        object InvalidCoordinates : Poi()
        object EmptyList : Poi()
    }
}