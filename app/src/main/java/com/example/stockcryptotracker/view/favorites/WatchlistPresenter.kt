package com.example.stockcryptotracker.view.favorites

import com.example.stockcryptotracker.service.YahooFinanceService


class WatchlistPresenter(val view: WatchlistView) {
    val yahooService = YahooFinanceService()

    fun start(symbols: MutableSet<String>?) {
        getWatchlistData(symbols)
    }

    fun getWatchlistData(symbols: MutableSet<String>?) {
        yahooService.getStockData(
            cleanData(symbols),
            successCallback = { data ->
                view.bindWatchlist(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

    fun cleanData(data: MutableSet<String>?): String {
        val trendingSymbolsNoSpaces = data?.joinToString()
            ?.filter { !it.isWhitespace() }
        return trendingSymbolsNoSpaces.toString()
    }
}

