package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.PharmacyNetworkDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.pharmacy.PharmacyGroup

interface PharmacyRepository {
    suspend fun getPharmacy(): Either<AppError, List<PharmacyGroup>>
}

class SharedPharmacyRepository(private val network: PharmacyNetworkDataSource) : PharmacyRepository {
    override suspend fun getPharmacy(): Either<AppError, List<PharmacyGroup>> =
        network.getPharmacy()
}