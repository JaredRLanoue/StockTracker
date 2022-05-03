package com.example.stockcryptotracker.view.watchlist

import com.example.stockcryptotracker.dto.FinanceData

interface WatchlistView {
    fun showError(errorMessage: String)
    fun bindWatchlist(data: FinanceData)
    fun showEmptyWatchlistError()
}
