package com.badajoz.badajozentubolsillo.datasource.local

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.Success
import com.badajoz.badajozentubolsillo.model.category.fmd.FmdUser
import com.russhwolf.settings.Settings
import com.russhwolf.settings.contains

interface FmdLocalDataSource {
    suspend fun isUserLoggedIn(): Either<AppError, Boolean>
    suspend fun getUser(): Either<AppError, FmdUser>
    suspend fun saveUser(user: FmdUser): Either<AppError, Success>
}

class SharedFmdLocalDataSource(private val settings: Settings) : FmdLocalDataSource {

    companion object {
        private const val USERNAME_KEY = "username"
        private const val PASSWORD_KEY = "password"
    }

    override suspend fun isUserLoggedIn(): Either<AppError, Boolean> =
        try {
            Either.Right(settings.contains(USERNAME_KEY) && settings.contains(PASSWORD_KEY))
        } catch (e: Exception) {
            Either.Left(AppError.LocalError)
        }

    override suspend fun getUser(): Either<AppError, FmdUser> =
        try {
            val username = settings.getString(USERNAME_KEY, "")
            val password = settings.getString(PASSWORD_KEY, "")
            Either.Right(FmdUser(username, password))
        } catch (e: Exception) {
            Either.Left(AppError.LocalError)
        }

    override suspend fun saveUser(user: FmdUser): Either<AppError, Success> =
        try {
            settings.putString(USERNAME_KEY, user.username)
            settings.putString(PASSWORD_KEY, user.password)
            Either.Right(Success)
        } catch (e: Exception) {
            Either.Left(AppError.LocalError)
        }
}