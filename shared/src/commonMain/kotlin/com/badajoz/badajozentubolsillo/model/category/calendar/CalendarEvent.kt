package com.badajoz.badajozentubolsillo.model.category.calendar


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarEvent(
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
)