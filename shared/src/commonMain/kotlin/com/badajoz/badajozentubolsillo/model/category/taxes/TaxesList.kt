package com.badajoz.badajozentubolsillo.model.category.taxes


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxesList(
    @SerialName("taxes")
    val taxes: List<TaxGroup>
) : Encryptable()