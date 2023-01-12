package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxGroup
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxesList
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BuildType
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.withPath
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

interface TaxNetworkDataSource : NetworkDataSource {
    suspend fun getTaxes(): Either<AppError, List<TaxGroup>>
}

class SharedTaxNetworkDataSource(private val buildType: BuildType, private val appConfig: AppConfig) :
    TaxNetworkDataSource {
    override suspend fun getTaxes(): Either<AppError, List<TaxGroup>> = execute(appConfig) { config ->
        buildClientWithAuth(BASE_URL, config.user, config.pass, buildType).use {
            it.get {
                url.withPath(Uris.Taxes)
            }.body<EncryptedNetworkResponse>().result.decrypt<TaxesList>(config.key).taxes
        }
    }
}