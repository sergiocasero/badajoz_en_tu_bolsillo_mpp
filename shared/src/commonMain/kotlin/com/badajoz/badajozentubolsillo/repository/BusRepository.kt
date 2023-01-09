package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.bus.BusLine
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop

interface BusRepository {
    suspend fun getBusLines(): Either<AppError, List<BusLine>>
    suspend fun getBusStops(): Either<AppError, List<BusStop>>
    suspend fun getFavoriteBusStops(): Either<AppError, List<BusStop>>
}