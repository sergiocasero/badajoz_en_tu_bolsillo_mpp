package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.FmdNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.local.FmdLocalDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.Success
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdCenter
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdSport
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser

interface FmdRepository {
    suspend fun getSports(): Either<AppError, List<FmdSport>>
    suspend fun saveUser(user: FmdUser): Either<AppError, Success>
    suspend fun isUserLoggedIn(): Either<AppError, Boolean>
    suspend fun getUser(): Either<AppError, FmdUser>
    suspend fun getSportDetail(sportId: Int): Either<AppError, FmdCenter>
}

class SharedFmdRepository(
    private val local: FmdLocalDataSource,
    private val network: FmdNetworkDataSource
) : FmdRepository {
    override suspend fun getSports(): Either<AppError, List<FmdSport>> =
        network.getSports()

    override suspend fun saveUser(user: FmdUser): Either<AppError, Success> =
        local.saveUser(user)

    override suspend fun isUserLoggedIn(): Either<AppError, Boolean> =
        local.isUserLoggedIn()

    override suspend fun getUser(): Either<AppError, FmdUser> =
        local.getUser()

    override suspend fun getSportDetail(sportId: Int): Either<AppError, FmdCenter> {
        TODO("Not yet implemented")
    }
}