package com.badajoz.badajozentubolsillo.model.category.bus


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusLines(
    @SerialName("lines")
    val lines: List<com.badajoz.badajozentubolsillo.model.category.bus.BusLine>
)