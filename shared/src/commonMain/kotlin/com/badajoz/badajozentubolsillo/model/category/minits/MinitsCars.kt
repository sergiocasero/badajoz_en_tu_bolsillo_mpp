package com.badajoz.badajozentubolsillo.model.category.minits


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinitsCars(
    @SerialName("cars")
    val cars: List<com.badajoz.badajozentubolsillo.model.category.minits.MinitsCar>
)