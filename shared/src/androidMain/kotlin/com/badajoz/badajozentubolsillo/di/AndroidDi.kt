package com.badajoz.badajozentubolsillo.di

import com.badajoz.badajozentubolsillo.utils.Executor
import org.koin.core.module.Module

actual fun Module.platformModule() {
    single { Executor() }
}