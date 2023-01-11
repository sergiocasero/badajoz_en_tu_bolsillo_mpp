package com.badajoz.badajozentubolsillo.model.category.fmd


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdSportDay(
    @SerialName("data")
    val `data`: List<FmdSlot>,
    @SerialName("id")
    val id: Int,
    @SerialName("image")
    val image: String,
    @SerialName("title")
    val title: String,
    val expanded: Boolean = false
) : Encryptable()