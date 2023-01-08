package com.badajoz.badajozentubolsillo.model.category.taxes


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxGroup(
    @SerialName("child")
    val child: List<TaxChild>,
    @SerialName("header")
    val header: String,
    @SerialName("links")
    val links: List<TaxLink>,
    @SerialName("short_description")
    val shortDescription: String
) : Encryptable()