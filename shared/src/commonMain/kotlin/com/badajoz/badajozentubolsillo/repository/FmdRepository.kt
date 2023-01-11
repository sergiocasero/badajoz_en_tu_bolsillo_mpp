package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.FmdNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.local.FmdLocalDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.Success
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterDetail
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenterItem
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSportDetail
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser

interface FmdRepository {
    suspend fun getCenters(): Either<AppError, List<FmdCenterItem>>
    suspend fun getCenterDetail(centerId: Int): Either<AppError, FmdCenterDetail>
    suspend fun getSportDetail(centerId: Int, sportId: Int): Either<AppError, FmdSportDetail>
    suspend fun saveUser(user: FmdUser): Either<AppError, Success>
    suspend fun isUserLoggedIn(): Either<AppError, Boolean>
    suspend fun getUser(): Either<AppError, FmdUser>
}

class SharedFmdRepository(
    private val local: FmdLocalDataSource,
    private val network: FmdNetworkDataSource
) : FmdRepository {

    override suspend fun getCenters(): Either<AppError, List<FmdCenterItem>> =
        network.getCenters()

    override suspend fun getCenterDetail(centerId: Int): Either<AppError, FmdCenterDetail> =
        network.getCenterDetail(centerId)

    override suspend fun saveUser(user: FmdUser): Either<AppError, Success> =
        local.saveUser(user)

    override suspend fun isUserLoggedIn(): Either<AppError, Boolean> =
        local.isUserLoggedIn()

    override suspend fun getUser(): Either<AppError, FmdUser> =
        local.getUser()

    override suspend fun getSportDetail(centerId: Int, sportId: Int): Either<AppError, FmdSportDetail> =
        network.getSportDetail(centerId, sportId)
}