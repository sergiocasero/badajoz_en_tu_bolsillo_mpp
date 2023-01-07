package com.badajoz.badajozentubolsillo.model.category.bus


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusStops(
    @SerialName("stops")
    val stops: List<com.badajoz.badajozentubolsillo.model.category.bus.BusStop>
)