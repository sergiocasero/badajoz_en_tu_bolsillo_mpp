package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either

actual class SharedAppConfig : AppConfig {
    override suspend fun getAppConfigData(): Either<AppError, AppConfigData> {
        TODO("Not yet implemented")
    }
}