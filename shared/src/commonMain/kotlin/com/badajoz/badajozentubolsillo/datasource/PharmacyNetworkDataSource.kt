package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.pharmacy.PharmacyGroup
import com.badajoz.badajozentubolsillo.model.category.pharmacy.PharmacyList
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BuildType
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.execute
import com.badajoz.badajozentubolsillo.utils.withPath
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

interface PharmacyNetworkDataSource : NetworkDataSource {
    suspend fun getPharmacy(): Either<AppError, List<PharmacyGroup>>
}

class SharedPharmacyNetworkDataSource(private val buildType: BuildType) : PharmacyNetworkDataSource {
    override suspend fun getPharmacy(): Either<AppError, List<PharmacyGroup>> = execute {
        buildClientWithAuth(BASE_URL, buildType).use {
            it.get {
                url.withPath(Uris.Pharmacy)
            }.body<EncryptedNetworkResponse>().result.decrypt<PharmacyList>().pharmacy
        }
    }
}