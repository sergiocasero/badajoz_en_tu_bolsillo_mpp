package com.badajoz.badajozentubolsillo.datasource

object Uris {
    object News {
        fun page(id: Int) = "/api/news/$id"
        const val detail = "/api/news/detail"
    }

    const val Calendar = "/api/calendar"
    const val Taxes = "/api/taxes"
    const val Biba = "/api/biba"
}