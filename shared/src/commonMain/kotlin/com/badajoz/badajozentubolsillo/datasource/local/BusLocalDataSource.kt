package com.badajoz.badajozentubolsillo.datasource.local

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop

interface BusLocalDataSource {
    fun getFavoriteBusStops(): Either<AppError, List<BusStop>>
}

class SharedBusLocalDataSource : BusLocalDataSource {
    override fun getFavoriteBusStops(): Either<AppError, List<BusStop>> = Either.Right(emptyList())
}