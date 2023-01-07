package com.badajoz.badajozentubolsillo.model

import kotlinx.serialization.Serializable

@Serializable
data class EncryptedNetworkRequest(val request: String)