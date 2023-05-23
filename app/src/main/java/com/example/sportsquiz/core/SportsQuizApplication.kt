package com.example.sportsquiz.core

import android.app.Application
import com.example.sportsquiz.core.di.repositoryModule
import com.example.sportsquiz.core.di.viewModelModule
import com.example.sportsquiz.core.di.wallpaperSourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.java.KoinAndroidApplication
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class SportsQuizApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SportsQuizApplication)
                .modules(
                    repositoryModule,
                    viewModelModule,
                    wallpaperSourceModule
                )
        }
    }

}