package com.badajoz.badajozentubolsillo.model.category.pharmacy


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pharmacy(
    @SerialName("address")
    val address: String
) : Encryptable()