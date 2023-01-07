package com.badajoz.badajozentubolsillo.model.category.bike


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BikeStation(
    @SerialName("availableBikes")
    val availableBikes: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("images")
    val images: List<String>,
    @SerialName("lat")
    val lat: Double,
    @SerialName("lng")
    val lng: Double,
    @SerialName("name")
    val name: String,
    @SerialName("notAvailableBikes")
    val notAvailableBikes: Int
)