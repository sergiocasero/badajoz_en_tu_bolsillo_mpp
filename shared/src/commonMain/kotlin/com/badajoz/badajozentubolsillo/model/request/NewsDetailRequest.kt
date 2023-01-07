package com.badajoz.badajozentubolsillo.model.request


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDetailRequest(
    @SerialName("link")
    val link: String
) : Encryptable()