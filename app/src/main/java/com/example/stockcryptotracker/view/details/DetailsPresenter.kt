package com.example.stockcryptotracker.view.details

import android.content.Context
import android.content.SharedPreferences
import com.example.stockcryptotracker.service.YahooFinanceService


class DetailsPresenter(val view: DetailsView) {
    val yahooService = YahooFinanceService()

    fun start(id: String) {
        getStockData(id)
        //getChartData()
    }

    // remove once working
    fun getSharedPref(context: Context, id: String): SharedPreferences? {
        return context.getSharedPreferences("id", Context.MODE_PRIVATE)
    }

    fun getSharedPrefFavoritesSet(prefs: SharedPreferences?): MutableList<String> {
        val favoriteSet: Set<String> = prefs!!.getStringSet("id", HashSet()) as Set<String>
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

    fun getChartData() {
        yahooService.getChartData(
            successCallback = { data ->
                view.bindChartData(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

}

