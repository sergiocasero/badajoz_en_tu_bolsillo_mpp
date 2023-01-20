package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppConfigData
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class SharedAppConfig : AppConfig {

    private val remoteConfig by lazy { Firebase.remoteConfig }

    companion object {
        private const val BASIC_AUTH_USER = "BASIC_AUTH_USER"
        private const val BASIC_AUTH_PASS = "BASIC_AUTH_PASSWORD"
        private const val ENCRYPTION_KEY = "ENCRYPTION_KEY"
        private const val ENCRYPTION_IV = "ENCRYPTION_IV"
        private const val LATEST_VERSION = "LATEST_VERSION"
        private const val IS_LATEST_VERSION_MANDATORY = "IS_LATEST_VERSION_MANDATORY"
    }

    override suspend fun getAppConfigData(): Either<AppError, AppConfigData> =
        suspendCancellableCoroutine { continuation ->
            remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val data = AppConfigData(
                        user = remoteConfig.getString(BASIC_AUTH_USER),
                        pass = remoteConfig.getString(BASIC_AUTH_PASS),
                        key = remoteConfig.getString(ENCRYPTION_KEY),
                        iv = remoteConfig.getString(ENCRYPTION_IV),
                        latestVersion = remoteConfig.getLong(LATEST_VERSION).toInt(),
                        isLatestVersionMandatory = remoteConfig.getBoolean(IS_LATEST_VERSION_MANDATORY)
                    )
                    continuation.resume(Either.Right(data))
                } else {
                    continuation.resume(Either.Left(AppError.AppConfig))
                }
            }
        }
}