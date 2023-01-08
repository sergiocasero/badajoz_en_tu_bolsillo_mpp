package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.CalendarNetworkDataSource
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.calendar.CalendarItem

interface CalendarRepository {
    suspend fun getCalendar(): Either<AppError, List<CalendarItem>>
}

class SharedCalendarRepository(private val network: CalendarNetworkDataSource) : CalendarRepository {
    override suspend fun getCalendar(): Either<AppError, List<CalendarItem>> =
        network.getCalendar()
}