package com.badajoz.badajozentubolsillo.model.request


import com.badajoz.badajozentubolsillo.model.Encryptable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdUser(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
) : Encryptable()