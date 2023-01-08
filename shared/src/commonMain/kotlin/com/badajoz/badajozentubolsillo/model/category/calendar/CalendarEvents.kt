package com.badajoz.badajozentubolsillo.model.category.calendar


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CalendarEvents(
    @SerialName("events")
    val events: List<CalendarEvent>
) : Encryptable()