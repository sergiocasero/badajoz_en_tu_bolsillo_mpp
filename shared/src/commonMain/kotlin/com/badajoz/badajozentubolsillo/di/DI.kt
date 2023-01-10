package com.badajoz.badajozentubolsillo.di

import com.badajoz.badajozentubolsillo.datasource.BikeNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.BusNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.CalendarNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.NewsNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.PharmacyNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedBikeNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedBusNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedCalendarNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedNewsNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedPharmacyNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedTaxNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.TaxNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.local.BusLocalDataSource
import com.badajoz.badajozentubolsillo.datasource.local.SharedBusLocalDataSource
import com.badajoz.badajozentubolsillo.repository.BikeRepository
import com.badajoz.badajozentubolsillo.repository.BusRepository
import com.badajoz.badajozentubolsillo.repository.CalendarRepository
import com.badajoz.badajozentubolsillo.repository.NewsRepository
import com.badajoz.badajozentubolsillo.repository.PharmacyRepository
import com.badajoz.badajozentubolsillo.repository.SharedBikeRepository
import com.badajoz.badajozentubolsillo.repository.SharedBusRepository
import com.badajoz.badajozentubolsillo.repository.SharedCalendarRepository
import com.badajoz.badajozentubolsillo.repository.SharedNewsRepository
import com.badajoz.badajozentubolsillo.repository.SharedPharmacyRepository
import com.badajoz.badajozentubolsillo.repository.SharedTaxRepository
import com.badajoz.badajozentubolsillo.repository.TaxRepository
import com.russhwolf.settings.Settings
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun Module.platformModule()

val sharedModule = module {
    platformModule()

    single { Settings() }

    single<NewsNetworkDataSource> { SharedNewsNetworkDataSource(buildType = get()) }
    single<NewsRepository> { SharedNewsRepository(network = get()) }

    single<CalendarNetworkDataSource> { SharedCalendarNetworkDataSource(buildType = get()) }
    single<CalendarRepository> { SharedCalendarRepository(network = get()) }

    single<TaxNetworkDataSource> { SharedTaxNetworkDataSource(buildType = get()) }
    single<TaxRepository> { SharedTaxRepository(network = get()) }

    single<BikeNetworkDataSource> { SharedBikeNetworkDataSource(buildType = get()) }
    single<BikeRepository> { SharedBikeRepository(network = get()) }

    single<BusLocalDataSource> { SharedBusLocalDataSource(settings = get()) }
    single<BusNetworkDataSource> { SharedBusNetworkDataSource(buildType = get()) }
    single<BusRepository> { SharedBusRepository(local = get(), network = get()) }

    single<PharmacyNetworkDataSource> { SharedPharmacyNetworkDataSource(buildType = get()) }
    single<PharmacyRepository> { SharedPharmacyRepository(network = get()) }
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