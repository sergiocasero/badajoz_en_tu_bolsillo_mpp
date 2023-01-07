package com.badajoz.badajozentubolsillo.model.category.news


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsImg(
    @SerialName("alt")
    val alt: String,
    @SerialName("url")
    val url: String
)