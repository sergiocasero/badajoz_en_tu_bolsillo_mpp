package com.badajoz.badajozentubolsillo.utils

import com.badajoz.badajozentubolsillo.model.Encryptable

const val BASE_URL = "https://badajoz.sergiocasero.es"
const val BASIC_AUTH_USER = "android"
const val BASIC_AUTH_PASSWORD = "Z8y35hlksjdhg%(%b"
const val ENCRYPTION_KEY = "Rnt}usy@p0PKHX9EugXE~XoMb6JL.Cz5"
const val ENCRYPTION_IV = "durn58fjkkhH5JK1"

// encrypt any T object with aes-256-cbc encryption
expect inline fun <reified T : Encryptable> T.encrypt(production: Boolean = true): String

expect inline fun <reified T: Encryptable> String.decrypt(production: Boolean = true): T