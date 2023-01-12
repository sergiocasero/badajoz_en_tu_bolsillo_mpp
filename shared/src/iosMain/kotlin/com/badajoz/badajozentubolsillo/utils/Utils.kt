package com.badajoz.badajozentubolsillo.utils

import com.badajoz.badajozentubolsillo.model.Encryptable

actual inline fun <reified T : Encryptable> T.encrypt(iv: String, key: String, production: Boolean): String {
    return "encrypted"
}

actual inline fun <reified T : Encryptable> String.decrypt(key: String, production: Boolean): T {
    TODO()
}
