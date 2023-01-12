package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterDetail
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterItem
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenters
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSportDetail
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser
import com.badajoz.badajozentubolsillo.model.request.EncryptedNetworkRequest
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BuildType
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.encrypt
import com.badajoz.badajozentubolsillo.utils.withPath
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.utils.io.core.use

interface FmdNetworkDataSource : NetworkDataSource {
    suspend fun getCenters(): Either<AppError, List<FmdCenterItem>>
    suspend fun getCenterDetail(centerId: Int): Either<AppError, FmdCenterDetail>
    suspend fun getSportDetail(fmdUser: FmdUser, centerId: Int, sportId: Int): Either<AppError, FmdSportDetail>
}

class SharedFmdNetworkDataSource(private val buildType: BuildType, private val appConfig: AppConfig) :
    FmdNetworkDataSource {
    override suspend fun getCenterDetail(centerId: Int): Either<AppError, FmdCenterDetail> =
        execute(appConfig) { config ->
            buildClientWithAuth(BASE_URL, config.user, config.pass, buildType).use {
                it.get {
                    url.withPath(Uris.Fmd.centerDetail(centerId))
                }.body<EncryptedNetworkResponse>().result.decrypt(config.key)
            }
        }

    override suspend fun getCenters(): Either<AppError, List<FmdCenterItem>> = execute(appConfig) { config ->
        buildClientWithAuth(BASE_URL, config.user, config.pass, buildType).use {
            it.get {
                url.withPath(Uris.Fmd.Centers)
            }.body<EncryptedNetworkResponse>().result.decrypt<FmdCenters>(config.key).centers
        }
    }

    override suspend fun getSportDetail(fmdUser: FmdUser, centerId: Int, sportId: Int): Either<AppError,
            FmdSportDetail> = execute(appConfig) { config ->
        buildClientWithAuth(BASE_URL, config.user, config.pass, buildType).use {
            it.post {
                url.withPath(Uris.Fmd.sportDetail(centerId, sportId))
                setBody(EncryptedNetworkRequest(fmdUser.encrypt(key = config.key, iv = config.iv)))
            }.body<EncryptedNetworkResponse>().result.decrypt(config.key)
        }
    }
}