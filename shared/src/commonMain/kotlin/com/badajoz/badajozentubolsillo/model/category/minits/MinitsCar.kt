package com.badajoz.badajozentubolsillo.model.category.minits


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MinitsCar(
    @SerialName("available")
    val available: Boolean,
    @SerialName("battery")
    val battery: Double,
    @SerialName("id")
    val id: Int,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("offer")
    val offer: Boolean,
    @SerialName("plate")
    val plate: String,
    @SerialName("price")
    val price: Double,
    @SerialName("standby_price")
    val standbyPrice: Double
) : Encryptable()