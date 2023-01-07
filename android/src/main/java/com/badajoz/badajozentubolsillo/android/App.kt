package com.badajoz.badajozentubolsillo.android

import android.app.Application
import com.badajoz.badajozentubolsillo.di.initKoin
import com.badajoz.badajozentubolsillo.utils.BuildType
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(
            appModule = module {
                // App dependencies, like build type
                single { BuildType.PRO }
            }
        ).apply {
            androidContext(this@App)
        }
    }
}