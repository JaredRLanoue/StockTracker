package com.example.stockcryptotracker.view

import com.example.stockcryptotracker.dto.FinanceData

interface HomeView {
    fun showError(errorMessage: String)
    fun bindUSMarket(data: FinanceData)
}
