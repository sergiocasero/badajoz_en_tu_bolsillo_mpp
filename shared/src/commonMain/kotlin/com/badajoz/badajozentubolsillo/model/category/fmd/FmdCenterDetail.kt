package com.badajoz.badajozentubolsillo.model.category.fmd


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdCenterDetail(
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("location")
    val location: String,
    @SerialName("phone")
    val phone: String,
    @SerialName("sports")
    val sports: List<FmdSportItem>,
    @SerialName("title")
    val title: String
): Encryptable()