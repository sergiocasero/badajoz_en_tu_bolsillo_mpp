package com.badajoz.badajozentubolsillo.model.category.news


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsCategory(
    @SerialName("color")
    val color: String,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)