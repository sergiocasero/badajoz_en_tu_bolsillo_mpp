package com.badajoz.badajozentubolsillo.model.category.news


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsPage(
    @SerialName("news")
    val news: List<com.badajoz.badajozentubolsillo.model.category.news.News>,
    @SerialName("next")
    val next: Int,
    @SerialName("prev")
    val prev: Int
)