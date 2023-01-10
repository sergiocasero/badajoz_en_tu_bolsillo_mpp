package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.BusNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.local.BusLocalDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.Success
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineDetail
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineItem
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.model.category.bus.BusTime

interface BusRepository {
    suspend fun getBusLines(): Either<AppError, List<BusLineItem>>
    suspend fun getBusLineDetail(lineId: Int): Either<AppError, BusLineDetail>
    suspend fun getFavoriteBusStops(): Either<AppError, List<BusStop>>
    suspend fun getStopTimes(lineId: Int, stopId: Int): Either<AppError, List<BusTime>>
    suspend fun saveFavoriteStop(stop: BusStop): Either<AppError, Success>

    suspend fun removeFavoriteStop(stop: BusStop): Either<AppError, Success>
}

class SharedBusRepository(private val local: BusLocalDataSource, private val network: BusNetworkDataSource) :
    BusRepository {
    override suspend fun getBusLines(): Either<AppError, List<BusLineItem>> =
        network.getBusLines()

    override suspend fun getBusLineDetail(lineId: Int): Either<AppError, BusLineDetail> =
        local.getFavoriteBusStops()
            .ifRight { localResult ->
                network.getBusLineDetail(lineId)
                    .ifRight {
                        val favoriteStops = localResult.success
                        val networkStops = it.success.stops
                        networkStops.forEach { stop ->
                            stop.favorite = favoriteStops.any { favoriteStop -> favoriteStop.id == stop.id }
                        }

                        Either.Right(it.success.copy(stops = networkStops))
                    }
            }

    override suspend fun getFavoriteBusStops(): Either<AppError, List<BusStop>> =
        local.getFavoriteBusStops()

    override suspend fun getStopTimes(lineId: Int, stopId: Int): Either<AppError, List<BusTime>> =
        network.getStopTimes(lineId, stopId)

    override suspend fun saveFavoriteStop(stop: BusStop): Either<AppError, Success> =
        local.saveFavoriteStop(stop)

    override suspend fun removeFavoriteStop(stop: BusStop): Either<AppError, Success> =
        local.removeFavoriteStop(stop)
}