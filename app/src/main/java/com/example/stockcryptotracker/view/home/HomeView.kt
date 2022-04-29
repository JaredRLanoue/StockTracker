package com.example.stockcryptotracker.view.home

import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.MarketData

interface HomeView {
    fun showError(errorMessage: String)
    fun bindMarketSummary(data: MarketData)
    fun bindTrending(data: FinanceData)
}
