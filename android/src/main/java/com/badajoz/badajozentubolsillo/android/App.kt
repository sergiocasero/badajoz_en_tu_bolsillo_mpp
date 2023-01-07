package com.badajoz.badajozentubolsillo.android

import android.app.Application
import com.badajoz.badajozentubolsillo.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            appModule = module {
                // App dependencies, like build type
            }
        ).apply {
            androidContext(this@App)
        }
    }
}