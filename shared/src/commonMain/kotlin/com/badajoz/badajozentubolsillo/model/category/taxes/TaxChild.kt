package com.badajoz.badajozentubolsillo.model.category.taxes


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxChild(
    @SerialName("links")
    val links: List<TaxLink>,
    @SerialName("title")
    val title: String
) : Encryptable()