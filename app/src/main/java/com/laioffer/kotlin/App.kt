package com.laioffer.kotlin

import android.app.Application

class App : Application() {

    companion object {
        lateinit var instance: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}