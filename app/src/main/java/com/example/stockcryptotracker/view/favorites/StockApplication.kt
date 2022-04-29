package com.example.stockcryptotracker.view.favorites

import android.app.Application
import android.content.Context

class StockApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        StockApplication.appContext = applicationContext
    }
    companion object {
        lateinit var appContext: Context
    }
}