package com.badajoz.badajozentubolsillo.model.category.news


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsPage(
    @SerialName("news")
    val news: List<News>,
    @SerialName("next")
    val next: Int,
    @SerialName("prev")
    val prev: Int
) : Encryptable()