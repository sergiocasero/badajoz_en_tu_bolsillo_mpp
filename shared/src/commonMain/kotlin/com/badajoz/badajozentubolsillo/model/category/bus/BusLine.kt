package com.badajoz.badajozentubolsillo.model.category.bus


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusLine(
    @SerialName("code")
    val code: Int,
    @SerialName("description")
    val description: String,
    @SerialName("id")
    val id: Int,
    @SerialName("more")
    val more: String,
    @SerialName("name")
    val name: String
)