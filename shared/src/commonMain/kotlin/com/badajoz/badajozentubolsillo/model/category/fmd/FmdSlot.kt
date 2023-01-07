package com.badajoz.badajozentubolsillo.model.category.fmd


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdSlot(
    @SerialName("available")
    val available: Boolean,
    @SerialName("court")
    val court: String,
    @SerialName("hour")
    val hour: String
)