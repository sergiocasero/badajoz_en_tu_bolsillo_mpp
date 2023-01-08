package com.badajoz.badajozentubolsillo.model.category.pharmacy


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PharmacyList(
    @SerialName("pharmacy")
    val pharmacy: List<PharmacyGroup>
) : Encryptable()