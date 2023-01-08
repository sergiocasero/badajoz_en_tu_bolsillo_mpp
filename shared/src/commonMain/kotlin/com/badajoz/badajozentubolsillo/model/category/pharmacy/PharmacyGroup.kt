package com.badajoz.badajozentubolsillo.model.category.pharmacy


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyGroup(
    @SerialName("items")
    val items: List<Pharmacy>,
    @SerialName("openingTime")
    val openingTime: String,
    @SerialName("title")
    val title: String
) : Encryptable()
