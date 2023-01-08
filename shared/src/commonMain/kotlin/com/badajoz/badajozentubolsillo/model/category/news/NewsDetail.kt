package com.badajoz.badajozentubolsillo.model.category.news


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDetail(
    @SerialName("content")
    val content: String,
    @SerialName("datetime")
    val datetime: String,
    @SerialName("downloads")
    val downloads: List<NewsDownload>,
    @SerialName("img")
    val img: NewsImg,
    @SerialName("title")
    val title: String
) : Encryptable()