package com.badajoz.badajozentubolsillo.model.category.bus


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusLines(
    @SerialName("lines")
    val lines: List<BusLineItem>
) : Encryptable()