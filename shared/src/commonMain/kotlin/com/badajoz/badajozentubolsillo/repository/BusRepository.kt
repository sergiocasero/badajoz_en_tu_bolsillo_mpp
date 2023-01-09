package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.BusNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.local.BusLocalDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.Success
import com.badajoz.badajozentubolsillo.model.category.bus.BusLine
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.model.category.bus.BusTime

interface BusRepository {
    suspend fun getBusLines(): Either<AppError, List<BusLine>>
    suspend fun getBusStops(lineId: Int): Either<AppError, List<BusStop>>
    suspend fun getFavoriteBusStops(): Either<AppError, List<BusStop>>
    suspend fun getStopTimes(lineId: Int, stopId: Int): Either<AppError, List<BusTime>>
    suspend fun saveFavoriteStop(stop: BusStop): Either<AppError, Success>
}

class SharedBusRepository(private val local: BusLocalDataSource, private val network: BusNetworkDataSource) :
    BusRepository {
    override suspend fun getBusLines(): Either<AppError, List<BusLine>> =
        network.getBusLines()

    override suspend fun getBusStops(lineId: Int): Either<AppError, List<BusStop>> =
        network.getBusStops(lineId)

    override suspend fun getFavoriteBusStops(): Either<AppError, List<BusStop>> =
        local.getFavoriteBusStops()

    override suspend fun getStopTimes(lineId: Int, stopId: Int): Either<AppError, List<BusTime>> =
        network.getStopTimes(lineId, stopId)

    override suspend fun saveFavoriteStop(stop: BusStop): Either<AppError, Success> {
        TODO("Not yet implemented")
    }
}