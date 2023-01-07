package com.badajoz.badajozentubolsillo.model.request

import kotlinx.serialization.Serializable

@Serializable
data class EncryptedNetworkRequest(val request: String)