package com.example.stockcryptotracker.view.home

import com.example.stockcryptotracker.dto.FinanceData

interface HomeView {
    fun showError(errorMessage: String)
    fun bindMarketSummary(data: FinanceData)
    fun bindTrending(data: FinanceData)
}
