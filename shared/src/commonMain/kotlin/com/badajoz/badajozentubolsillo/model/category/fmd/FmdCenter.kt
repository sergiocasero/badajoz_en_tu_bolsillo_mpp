package com.badajoz.badajozentubolsillo.model.category.fmd


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdCenter(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("location")
    val location: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("sports")
    val sports: List<com.badajoz.badajozentubolsillo.model.category.fmd.FmdSport>,
    @SerialName("title")
    val title: String
)