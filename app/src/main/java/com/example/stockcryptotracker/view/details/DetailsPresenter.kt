package com.example.stockcryptotracker.view.details

import com.example.stockcryptotracker.dto.TrendingData
import com.example.stockcryptotracker.service.YahooFinanceService
import com.example.stockcryptotracker.view.home.HomeView


class DetailsPresenter(val view: DetailsView) {
    val yahooService = YahooFinanceService()

    fun start() {
        getStockData()
        getChartData()
    }

    fun getStockData() {
        yahooService.getStockData(
            "",
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

