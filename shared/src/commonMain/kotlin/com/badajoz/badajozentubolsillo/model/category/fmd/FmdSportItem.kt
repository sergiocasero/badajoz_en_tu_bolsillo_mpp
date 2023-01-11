package com.badajoz.badajozentubolsillo.model.category.fmd


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdSportItem(
    @SerialName("centerId")
    val centerId: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("title")
    val title: String
) : Encryptable()