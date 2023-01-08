package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.BikeNetworkDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.bike.BikeStation

interface BikeRepository {
    suspend fun getBikeStations(): Either<AppError, List<BikeStation>>
}

class SharedBikeRepository(private val network: BikeNetworkDataSource): BikeRepository {
    override suspend fun getBikeStations(): Either<AppError, List<BikeStation>> =
        network.getBikeStations()
}