package com.badajoz.badajozentubolsillo.datasource

object Uris {
    object News {
        fun page(id: Int) = "/api/news/$id"
        const val detail = "/api/news/detail"
    }
}