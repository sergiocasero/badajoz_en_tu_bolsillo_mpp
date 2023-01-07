package com.badajoz.badajozentubolsillo.model.category.pharmacy


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pharmacy(
    @SerialName("address")
    val address: String
)