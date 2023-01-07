package com.badajoz.badajozentubolsillo.utils

import com.badajoz.badajozentubolsillo.model.Encryptable

actual inline fun <reified T : Encryptable> T.encrypt(production: Boolean): String {
    return "encrypted"
}

actual inline fun <reified T : Encryptable> String.decrypt(production: Boolean): T {
    TODO()
}
