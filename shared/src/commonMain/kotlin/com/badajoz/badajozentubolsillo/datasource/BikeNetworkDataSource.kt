package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStation
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStations
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BuildType
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.execute
import com.badajoz.badajozentubolsillo.utils.withPath
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

interface BikeNetworkDataSource : NetworkDataSource {
    suspend fun getBikeStations(): Either<AppError, List<BikeStation>>
}

class SharedBikeNetworkDataSource(private val buildType: BuildType) : BikeNetworkDataSource {
    override suspend fun getBikeStations(): Either<AppError, List<BikeStation>> = execute {
        buildClientWithAuth(BASE_URL, buildType).use {
            it.get {
                url.withPath(Uris.Biba)
            }.body<EncryptedNetworkResponse>().result.decrypt<BikeStations>().stations
        }
    }

}