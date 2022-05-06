package com.example.stockcryptotracker.view.home

import android.app.Application
import android.content.Context

class StockApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}