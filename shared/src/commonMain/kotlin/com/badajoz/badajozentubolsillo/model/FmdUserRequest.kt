package com.badajoz.badajozentubolsillo.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FmdUserRequest(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
) : Encryptable()