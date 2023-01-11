package com.badajoz.badajozentubolsillo.model.category.fmd


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdSportDetail(
    @SerialName("sport")
    val sport: List<FmdSportDay>
) : Encryptable()