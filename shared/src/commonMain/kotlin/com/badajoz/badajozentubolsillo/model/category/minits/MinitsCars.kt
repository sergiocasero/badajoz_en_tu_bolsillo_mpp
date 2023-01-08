package com.badajoz.badajozentubolsillo.model.category.minits


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinitsCars(
    @SerialName("cars")
    val cars: List<MinitsCar>
) : Encryptable()