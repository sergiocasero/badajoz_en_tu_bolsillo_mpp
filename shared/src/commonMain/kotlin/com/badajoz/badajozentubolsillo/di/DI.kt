package com.badajoz.badajozentubolsillo.di

import com.badajoz.badajozentubolsillo.datasource.NewsNetworkDataSource
import com.badajoz.badajozentubolsillo.datasource.SharedNewsNetworkDataSource
import com.badajoz.badajozentubolsillo.repository.NewsRepository
import com.badajoz.badajozentubolsillo.repository.SharedNewsRepository
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun Module.platformModule()

val sharedModule = module {
    platformModule()

    single<NewsNetworkDataSource> { SharedNewsNetworkDataSource(buildType = get()) }
    single<NewsRepository> { SharedNewsRepository(network = get()) }
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