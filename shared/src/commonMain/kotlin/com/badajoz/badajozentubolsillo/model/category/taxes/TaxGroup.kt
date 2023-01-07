package com.badajoz.badajozentubolsillo.model.category.taxes


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxGroup(
    @SerialName("child")
    val child: List<com.badajoz.badajozentubolsillo.model.category.taxes.TaxChild>,
    @SerialName("header")
    val header: String,
    @SerialName("links")
    val links: List<com.badajoz.badajozentubolsillo.model.category.taxes.TaxLink>,
    @SerialName("short_description")
    val shortDescription: String
)