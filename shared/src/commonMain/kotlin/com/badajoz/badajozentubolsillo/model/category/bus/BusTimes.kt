package com.badajoz.badajozentubolsillo.model.category.bus


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusTimes(
    @SerialName("times")
    val times: List<com.badajoz.badajozentubolsillo.model.category.bus.BusTime>
)