package com.example.sportsquiz.core

import android.app.Application
import android.content.Context
import com.example.sportsquiz.core.di.DataInjector

class SportsQuizApplication : Application() {

    companion object {

        private lateinit var dataInjector: DataInjector

        fun init(context: Context) {
            dataInjector = DataInjector(context)
        }

        fun getInjector() = dataInjector

    }

    override fun onCreate() {
        super.onCreate()
        init(this)
    }

}