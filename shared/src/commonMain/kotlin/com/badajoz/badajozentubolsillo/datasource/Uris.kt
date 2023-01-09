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
        fun stops(lineId: Int) = "/api/tubasa/$lineId/stops"
        fun times(lineId: Int, stopId: Int) = "/api/tubasa/$lineId/$stopId/times"
    }
}