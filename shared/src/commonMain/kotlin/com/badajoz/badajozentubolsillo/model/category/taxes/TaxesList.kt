package com.badajoz.badajozentubolsillo.model.category.taxes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxesList(
    @SerialName("taxes")
    val taxes: List<com.badajoz.badajozentubolsillo.model.category.taxes.TaxGroup>
)