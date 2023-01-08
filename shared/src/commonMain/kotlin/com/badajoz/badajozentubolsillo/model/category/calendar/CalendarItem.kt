package com.badajoz.badajozentubolsillo.model.category.calendar


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarItem(
    @SerialName("date")
    val date: String,
    @SerialName("location")
    val location: String,
    @SerialName("time")
    val time: String,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String
) : Encryptable()