package com.badajoz.badajozentubolsillo.datasource.local

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.Success
import com.badajoz.badajozentubolsillo.model.category.bus.BusStop
import com.badajoz.badajozentubolsillo.model.category.bus.BusStopsVo
import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains
import kotlinx.serialization.json.Json

interface BusLocalDataSource {
    fun getFavoriteBusStops(): Either<AppError, List<BusStop>>

    fun saveFavoriteStop(stop: BusStop): Either<AppError, Success>

    fun removeFavoriteStop(stop: BusStop): Either<AppError, Success>
}

class SharedBusLocalDataSource(private val settings: Settings) : BusLocalDataSource {

    companion object {
        private const val FAVORITE_STOPS = "favorite_stops"
    }

    override fun getFavoriteBusStops(): Either<AppError, List<BusStop>> = Either.Right(emptyList())

    override fun saveFavoriteStop(stop: BusStop): Either<AppError, Success> =
        try {
            val currentStops = getStopsVo()
            val newStops = currentStops.copy(stops = currentStops.stops + stop)
            setStopsVo(newStops)
            Either.Right(Success)
        } catch (e: Exception) {
            Either.Left(AppError.LocalError)
        }

    override fun removeFavoriteStop(stop: BusStop): Either<AppError, Success> =
        try {
            val currentStops = getStopsVo()
            val newStops = currentStops.copy(stops = currentStops.stops - stop)
            setStopsVo(newStops)
            Either.Right(Success)
        } catch (e: Exception) {
            Either.Left(AppError.LocalError)
        }

    private fun getStopsVo(): BusStopsVo {
        return if (settings.contains(FAVORITE_STOPS)) {
            val json = settings.getString(FAVORITE_STOPS, "")
            Json.decodeFromString(BusStopsVo.serializer(), json)
        } else {
            BusStopsVo(emptyList())
        }
    }

    private fun setStopsVo(stopsVo: BusStopsVo) {
        val stopsJson = Json.encodeToString(BusStopsVo.serializer(), stopsVo)
        settings.putString(FAVORITE_STOPS, stopsJson)
    }
}