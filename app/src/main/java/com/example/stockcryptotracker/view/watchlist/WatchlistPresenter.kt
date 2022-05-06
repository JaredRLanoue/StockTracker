package com.example.stockcryptotracker.view.watchlist

import com.example.stockcryptotracker.service.YahooFinanceService


class WatchlistPresenter(val view: WatchlistView, val yahooService: YahooFinanceService) {

    fun start() {
        getWatchlistData()
    }

    fun getWatchlistData() {
        val watchlist = yahooService.getWatchlistData()
        val watchlistString = watchlist.joinToString().filter { !it.isWhitespace() }

        if (watchlistString.isNotEmpty()) {
            yahooService.getStockData(
                watchlistString,
                successCallback = { data ->
                    view.bindWatchlist(data)
                },
                failureCallback = { errorMessage ->
                    view.showError(errorMessage)
                }
            )
        } else if (watchlistString.isEmpty()) {
            view.showEmptyWatchlistError()
        }
    }
}

