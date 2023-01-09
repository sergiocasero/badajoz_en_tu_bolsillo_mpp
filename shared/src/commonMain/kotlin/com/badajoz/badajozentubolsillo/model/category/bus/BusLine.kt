package com.badajoz.badajozentubolsillo.model.category.bus


import com.badajoz.badajozentubolsillo.model.Encryptable
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
    val name: String,
    @SerialName("image")
    val image: String,
    @SerialName("color")
    val color: String
) : Encryptable()