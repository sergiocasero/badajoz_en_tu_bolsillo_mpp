package com.badajoz.badajozentubolsillo.model.category.bike


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BikeStations(
    @SerialName("stations")
    val stations: List<com.badajoz.badajozentubolsillo.model.category.bike.BikeStation>
)