package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarEvents
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BuildType
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.withPath
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

interface CalendarNetworkDataSource : NetworkDataSource {
    suspend fun getCalendar(): Either<AppError, List<CalendarItem>>
}

class SharedCalendarNetworkDataSource(private val buildType: BuildType) : CalendarNetworkDataSource {

    override suspend fun getCalendar(): Either<AppError, List<CalendarItem>> = execute {
        buildClientWithAuth(BASE_URL, buildType).use {
            it.get {
                url.withPath(Uris.Calendar)
            }.body<EncryptedNetworkResponse>().result.decrypt<CalendarEvents>().events
        }
    }
}