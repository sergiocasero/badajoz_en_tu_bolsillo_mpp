package com.badajoz.badajozentubolsillo.utils

import com.badajoz.badajozentubolsillo.model.Encryptable
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import kotlinx.coroutines.flow.MutableStateFlow

const val BASE_URL = "https://badajoz.sergiocasero.es"

// encrypt any T object with aes-256-cbc encryption
expect inline fun <reified T : Encryptable> T.encrypt(iv: String, key: String, production: Boolean = true): String

expect inline fun <reified T : Encryptable> String.decrypt(key: String, production: Boolean = true): T

fun <T> MutableStateFlow<MutableList<T>>.withItemUpdated(item: T, where: (T) -> Boolean): MutableList<T> {
    val newList = mutableListOf<T>()
    newList.addAll(value)
    val index = newList.indexOfFirst { where(it) }
    newList[index] = item
    return newList
}


internal fun URLBuilder.withPath(path: String, block: URLBuilder.(URLBuilder) -> Unit = {}) {
    encodedPath = path
    block(this)
}

val <T> T.exhaustive: T
    get() = this