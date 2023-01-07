package com.badajoz.badajozentubolsillo.model.category.taxes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxChild(
    @SerialName("links")
    val links: List<com.badajoz.badajozentubolsillo.model.category.taxes.TaxLink>,
    @SerialName("title")
    val title: String
)