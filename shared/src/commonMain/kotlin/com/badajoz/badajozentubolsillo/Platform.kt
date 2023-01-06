package com.badajoz.badajozentubolsillo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform