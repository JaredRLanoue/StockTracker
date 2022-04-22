package com.example.stockcryptotracker.view.home

import com.example.stockcryptotracker.dto.TrendingData
import com.example.stockcryptotracker.service.YahooFinanceService


class HomePresenter(val view: HomeView) {
    val yahooService = YahooFinanceService()

    fun start() {
        getUSMarketData()
        getTrendingData()
    }

    fun getUSMarketData() {
        yahooService.getStockData(
            "^IXIC,^DJI,^GSPC,RTY=F,CL=F,GC=F,SI=F",
            successCallback = { data ->
                view.bindMarketSummary(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

    fun getTrendingData() {
        yahooService.getTrendingData(
            successCallback = { data ->
                yahooService.getStockData(
                    cleanData(data),
                    successCallback = { finalData ->
                        view.bindTrending(finalData)
                    },
                    failureCallback = { errorMessage ->
                        view.showError(errorMessage)
                    }
                )
            },
            failureCallback = { errorMessage ->
                view.showError(errorMessage).toString()
            }
        )
    }

    fun cleanData(data: TrendingData): String {
        val listOfTrendingSymbols: MutableList<String> = mutableListOf()
        for (i in 0..9) {
            listOfTrendingSymbols += data.finance.result[0].quotes[i].symbol
        }
        val trendingSymbolsNoSpaces = listOfTrendingSymbols.joinToString()
            .filter { !it.isWhitespace() }
        return trendingSymbolsNoSpaces
    }
}

