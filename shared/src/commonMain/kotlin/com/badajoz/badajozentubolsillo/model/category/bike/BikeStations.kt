package com.badajoz.badajozentubolsillo.model.category.bike


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BikeStations(
    @SerialName("stations")
    val stations: List<BikeStation>
) : Encryptable()