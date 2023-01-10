package com.badajoz.badajozentubolsillo.model.category.bus


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusStop(
    @SerialName("id")
    val id: Int,
    @SerialName("line")
    val line: Int,
    @SerialName("name")
    val name: String,
    @SerialName("favorite")
    var favorite: Boolean = false
) : Encryptable()