package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineDetail
import com.badajoz.badajozentubolsillo.model.category.bus.BusLineItem
import com.badajoz.badajozentubolsillo.model.category.bus.BusLines
import com.badajoz.badajozentubolsillo.model.category.bus.BusStopTimes
import com.badajoz.badajozentubolsillo.model.category.bus.BusTime
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BuildType
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.withPath
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

interface BusNetworkDataSource : NetworkDataSource {
    suspend fun getBusLines(): Either<AppError, List<BusLineItem>>
    suspend fun getBusLineDetail(lineId: Int): Either<AppError, BusLineDetail>
    suspend fun getStopTimes(lineId: Int, stopId: Int): Either<AppError, List<BusTime>>
}

class SharedBusNetworkDataSource(private val buildType: BuildType, private val appConfig: AppConfig) :
    BusNetworkDataSource {
    override suspend fun getBusLines(): Either<AppError, List<BusLineItem>> = execute(appConfig) { config ->
        buildClientWithAuth(BASE_URL, config.user, config.pass, buildType).use {
            it.get {
                url.withPath(Uris.Bus.Lines)
            }.body<EncryptedNetworkResponse>().result.decrypt<BusLines>(config.key).lines
        }
    }

    override suspend fun getBusLineDetail(lineId: Int): Either<AppError, BusLineDetail> = execute(appConfig) { config ->
        buildClientWithAuth(BASE_URL, config.user, config.pass, buildType).use {
            it.get {
                url.withPath(Uris.Bus.stops(lineId))
            }.body<EncryptedNetworkResponse>().result.decrypt(config.key)
        }
    }

    override suspend fun getStopTimes(lineId: Int, stopId: Int): Either<AppError, List<BusTime>> =
        execute(appConfig) { config ->
            buildClientWithAuth(BASE_URL, config.user, config.pass, buildType).use {
                it.get {
                    url.withPath(Uris.Bus.times(lineId, stopId))
                }.body<EncryptedNetworkResponse>().result.decrypt<BusStopTimes>(config.key).times
            }
        }

}