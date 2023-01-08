package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.TaxNetworkDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.taxes.TaxGroup

interface TaxRepository {
    suspend fun getTaxes(): Either<AppError, List<TaxGroup>>
}

class SharedTaxRepository(private val network: TaxNetworkDataSource) : TaxRepository {
    override suspend fun getTaxes(): Either<AppError, List<TaxGroup>> =
        network.getTaxes()
}