package com.badajoz.badajozentubolsillo.model.category.taxes


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaxLink(
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
) : Encryptable()