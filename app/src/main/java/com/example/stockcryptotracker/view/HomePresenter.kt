package com.example.stockcryptotracker.view

import com.example.stockcryptotracker.service.YahooFinanceService


class HomePresenter(val view: HomeView) {
    val yahooService = YahooFinanceService()
    fun start() {
        getUSMarketData()
    }

    fun getUSMarketData() {
        yahooService.getStockData(
            "^IXIC,^DJI,^GSPC,CL=F,GC=F,SI=F", // add RTY=F?

            successCallback = { data ->
                view.bindUSMarket(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

}