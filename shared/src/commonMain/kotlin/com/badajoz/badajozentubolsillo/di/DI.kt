package com.badajoz.badajozentubolsillo.di

import com.badajoz.badajozentubolsillo.datasource.BikeNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.CalendarNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.NewsNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedBikeNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedCalendarNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedNewsNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedTaxNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.TaxNetworkDataSource
import com.badajoz.badajozentubolsillo.repository.BikeRepository
import com.badajoz.badajozentubolsillo.repository.CalendarRepository
import com.badajoz.badajozentubolsillo.repository.NewsRepository
import com.badajoz.badajozentubolsillo.repository.SharedBikeRepository
import com.badajoz.badajozentubolsillo.repository.SharedCalendarRepository
import com.badajoz.badajozentubolsillo.repository.SharedNewsRepository
import com.badajoz.badajozentubolsillo.repository.SharedTaxRepository
import com.badajoz.badajozentubolsillo.repository.TaxRepository
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun Module.platformModule()

val sharedModule = module {
    platformModule()

    single<NewsNetworkDataSource> { SharedNewsNetworkDataSource(buildType = get()) }
    single<NewsRepository> { SharedNewsRepository(network = get()) }

    single<CalendarNetworkDataSource> { SharedCalendarNetworkDataSource(buildType = get()) }
    single<CalendarRepository> { SharedCalendarRepository(network = get()) }

    single<TaxNetworkDataSource> { SharedTaxNetworkDataSource(buildType = get()) }
    single<TaxRepository> { SharedTaxRepository(network = get()) }

    single<BikeNetworkDataSource> { SharedBikeNetworkDataSource(buildType = get()) }
    single<BikeRepository> { SharedBikeRepository(network = get()) }
}

fun initKoin(appModule: Module): KoinApplication {
    val koinApplication = startKoin {
        modules(
            appModule,
            sharedModule
        )
    }

    return koinApplication
}