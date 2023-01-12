package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either

interface AppConfig {
    suspend fun getAppConfigData(): Either<AppError, AppConfigData>
}

expect class SharedAppConfig() : AppConfig