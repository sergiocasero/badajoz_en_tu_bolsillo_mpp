package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.AppConfig
import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either

interface SplashRepository {
    suspend fun getAppConfig(): Either<AppError, AppConfigData>
}

class SharedSplashRepository(private val appConfig: AppConfig) : SplashRepository {
    override suspend fun getAppConfig(): Either<AppError, AppConfigData> =
        appConfig.getAppConfigData()
}