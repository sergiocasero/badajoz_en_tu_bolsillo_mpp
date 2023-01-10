package com.badajoz.badajozentubolsillo.utils

import com.badajoz.badajozentubolsillo.datasource.NetworkDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.Encryptable
import io.ktor.http.URLBuilder
import io.ktor.http.encodedPath
import kotlinx.coroutines.flow.MutableStateFlow

const val BASE_URL = "https://badajoz.sergiocasero.es"
const val BASIC_AUTH_USER = "android"
const val BASIC_AUTH_PASSWORD = "Z8y35hlksjdhg%(%b"
const val ENCRYPTION_KEY = "Rnt}usy@p0PKHX9EugXE~XoMb6JL.Cz5"
const val ENCRYPTION_IV = "durn58fjkkhH5JK1"

// encrypt any T object with aes-256-cbc encryption
expect inline fun <reified T : Encryptable> T.encrypt(production: Boolean = true): String

expect inline fun <reified T : Encryptable> String.decrypt(production: Boolean = true): T

internal suspend fun <R> NetworkDataSource.execute(block: suspend () -> R): Either<AppError, R> = try {
    Either.Right(block())
} catch (t: Throwable) {
    t.printStackTrace()
    Either.Left(
        when (t) {
            is AppError -> t
            else -> AppError.Unknown
        }
    )
}

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