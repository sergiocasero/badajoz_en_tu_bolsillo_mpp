package com.badajoz.badajozentubolsillo.model.category.news


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("category")
    val category: NewsCategory,
    @SerialName("date")
    val date: String,
    @SerialName("description")
    val description: String,
    @SerialName("link")
    val link: String,
    @SerialName("title")
    val title: String
) : Encryptable()