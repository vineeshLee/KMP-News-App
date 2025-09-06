package org.kmp.newsapp

import android.app.Application
import org.kmp.newsapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class MyApplication: Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidLogger()
            androidContext(this@MyApplication)
        }
    }
}