package com.badajoz.badajozentubolsillo.model.category.bus


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusTime(
    @SerialName("address")
    val address: String,
    @SerialName("distance")
    val distance: Int,
    @SerialName("line")
    val line: String,
    @SerialName("time")
    val time: String
) : Encryptable()