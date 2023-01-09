package com.badajoz.badajozentubolsillo.datasource

object Uris {
    object News {
        fun page(id: Int) = "/api/news/$id"
        const val detail = "/api/news/detail"
    }

    const val Calendar = "/api/calendar"
    const val Taxes = "/api/taxes"
    const val Biba = "/api/biba"

    object Bus {
        const val Lines = "/api/tubasa/lines"
        fun stops(lineId: String) = "/api/tubasa/$lineId/stops"
        fun times(lineId: String, stopId: String) = "/api/tubasa/$lineId/$stopId/times"
    }
}