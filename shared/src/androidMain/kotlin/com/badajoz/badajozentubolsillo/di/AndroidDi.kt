package com.badajoz.badajozentubolsillo.di

import com.badajoz.badajozentubolsillo.analytics.Analytics
import com.badajoz.badajozentubolsillo.analytics.SharedAnalytics
import com.badajoz.badajozentubolsillo.datasource.AppConfig
import com.badajoz.badajozentubolsillo.datasource.SharedAppConfig
import com.badajoz.badajozentubolsillo.utils.Executor
import org.koin.core.module.Module

actual fun Module.platformModule() {
    single { Executor() }
    single<Analytics> { SharedAnalytics() }
    single<AppConfig> { SharedAppConfig() }
}