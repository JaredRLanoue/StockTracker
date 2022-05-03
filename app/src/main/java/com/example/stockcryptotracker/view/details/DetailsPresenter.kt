package com.example.stockcryptotracker.view.details

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.stockcryptotracker.service.YahooFinanceService
import com.example.stockcryptotracker.view.home.StockApplication
import com.example.stockcryptotracker.view.home.StockApplication.Companion.appContext
import com.github.mikephil.charting.data.ChartData
import com.github.mikephil.charting.data.Entry


class DetailsPresenter(val view: DetailsView) {
    val yahooService = YahooFinanceService()

    fun start(id: String) {
        getStockData(id)
        getChartData(id)
        getStatsData(id)
    }

    // remove once working
    fun getSharedPref(): SharedPreferences? {
        return appContext.getSharedPreferences("watchlist", Context.MODE_PRIVATE)
    }

    fun getSharedPrefFavoritesSet(): MutableList<String> {
        val favoriteSet: Set<String> = appContext.getSharedPreferences("watchlist", Context.MODE_PRIVATE).getStringSet("watchlist", HashSet()) as Set<String>
        return favoriteSet.toMutableList()
    }

    fun getStockData(id: String) {
        yahooService.getStockData(
            id,
            successCallback = { data ->
                view.bindStockData(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

    fun getChartData(id: String) {
        yahooService.getChartData(
            id,
            successCallback = { data ->
                view.bindChartData(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

    fun getStatsData(id: String) {
        yahooService.getStatsData(
            id,
            successCallback = { data ->
                view.bindStatsData(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

}

