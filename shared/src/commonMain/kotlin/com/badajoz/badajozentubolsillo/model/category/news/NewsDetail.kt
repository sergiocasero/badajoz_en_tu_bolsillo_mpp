package com.badajoz.badajozentubolsillo.model.category.news


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDetail(
    @SerialName("content")
    val content: String,
    @SerialName("datetime")
    val datetime: String,
    @SerialName("downloads")
    val downloads: List<com.badajoz.badajozentubolsillo.model.category.news.NewsDownload>,
    @SerialName("img")
    val img: com.badajoz.badajozentubolsillo.model.category.news.NewsImg,
    @SerialName("title")
    val title: String
)