package com.example.stockcryptotracker.view.details

import com.example.stockcryptotracker.dto.ChartData
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.StatsData


interface DetailsView {
    fun showError(errorMessage: String)
    fun bindChartData(data: ChartData)
    fun bindStockData(data: FinanceData)
    fun bindStatsData(data: StatsData)
}
