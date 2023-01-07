package com.badajoz.badajozentubolsillo.model.category.bus


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusStop(
    @SerialName("id")
    val id: Int,
    @SerialName("line")
    val line: Int,
    @SerialName("name")
    val name: String
)