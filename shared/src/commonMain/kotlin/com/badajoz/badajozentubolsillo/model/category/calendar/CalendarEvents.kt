package com.badajoz.badajozentubolsillo.model.category.calendar


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarEvents(
    @SerialName("events")
    val events: List<com.badajoz.badajozentubolsillo.model.category.calendar.CalendarEvent>
)