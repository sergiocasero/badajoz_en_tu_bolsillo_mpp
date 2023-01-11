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
        fun times(lineId: Int, stopId: Int) = "/api/tubasa/lines/$lineId/stops/$stopId"
    }

    const val Pharmacy = "/api/pharmacy"

    object Fmd {
        const val Centers = "/api/fmd/centers"
        fun centerDetail(centerId: Int) = "/api/fmd/centers/$centerId"
        fun sportDetail(centerId: Int, sportId: Int) = "/api/fmd/centers/$centerId/sports/$sportId"
    }
}