package com.badajoz.badajozentubolsillo.model.category.pharmacy


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyList(
    @SerialName("pharmacy")
    val pharmacy: List<com.badajoz.badajozentubolsillo.model.category.pharmacy.PharmacyGroup>
)