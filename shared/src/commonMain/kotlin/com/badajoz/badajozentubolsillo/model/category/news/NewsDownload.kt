package com.badajoz.badajozentubolsillo.model.category.news


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDownload(
    @SerialName("link")
    val link: String,
    @SerialName("title")
    val title: String
)