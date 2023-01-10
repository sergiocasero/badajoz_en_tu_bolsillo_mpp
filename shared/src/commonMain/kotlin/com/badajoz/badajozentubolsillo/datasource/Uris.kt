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
        fun stops(lineId: Int) = "/api/tubasa/lines/$lineId"
        fun times(lineId: Int, stopId: Int) = "/api/tubasa/lines/$lineId/stop/$stopId/times"
    }

    const val Pharmacy = "/api/pharmacy"

    object Fmd {
        const val Sports = "/api/fmd/sports"
        fun availability(sportId: Int) = "/api/fmd/sports/$sportId"
    }
}