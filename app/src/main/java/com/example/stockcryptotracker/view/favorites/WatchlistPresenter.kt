package com.example.stockcryptotracker.view.favorites

import android.util.Log
import com.example.stockcryptotracker.service.YahooFinanceService


class WatchlistPresenter(val view: WatchlistView) {
    val yahooService = YahooFinanceService()

    fun start() {
        getWatchlistData()
    }

    fun getWatchlistData() {
        val watchlist = yahooService.getWatchlistData().joinToString()
            .filter { !it.isWhitespace() }

        if (watchlist.isNotEmpty()) {
            yahooService.getStockData(
                watchlist,
                successCallback = { data ->
                    view.bindWatchlist(data)
                },
                failureCallback = { errorMessage ->
                    view.showError(errorMessage)
                })
        } else if (watchlist.isEmpty()) {
            view.showEmptyWatchlistError()
        }
    }

//    private fun cleanData(data: MutableList<String>): String {
//        val trendingSymbolsNoSpaces = data.joinToString()
//            .filter { !it.isWhitespace() }
//        return trendingSymbolsNoSpaces.toString()
//    }
}

