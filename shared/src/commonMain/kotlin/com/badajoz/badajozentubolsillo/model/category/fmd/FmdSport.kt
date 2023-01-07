package com.badajoz.badajozentubolsillo.model.category.fmd


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdSport(
    @SerialName("days")
    val days: List<com.badajoz.badajozentubolsillo.model.category.fmd.FmdDay>,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("title")
    val title: String
)