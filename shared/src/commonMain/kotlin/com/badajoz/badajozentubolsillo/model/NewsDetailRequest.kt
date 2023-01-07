package com.badajoz.badajozentubolsillo.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDetailRequest(
    @SerialName("link")
    val link: String
) : Encryptable()