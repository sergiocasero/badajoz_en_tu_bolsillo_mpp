package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSport
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSports
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BuildType
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.withPath
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

interface FmdNetworkDataSource : NetworkDataSource {
    suspend fun getSports(): Either<AppError, List<FmdSport>>
}

class SharedFmdNetworkDataSource(private val buildType: BuildType) : FmdNetworkDataSource {
    override suspend fun getSports(): Either<AppError, List<FmdSport>> = execute {
        buildClientWithAuth(BASE_URL, buildType).use {
            it.get {
                url.withPath(Uris.Fmd.Sports)
            }.body<EncryptedNetworkResponse>().result.decrypt<FmdSports>().sports
        }
    }
}