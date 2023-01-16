package com.badajoz.badajozentubolsillo.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual fun Module.platformModule() {

}

fun initKoinIos() {
    initKoin(
        module {
        }
    )
}