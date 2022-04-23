package com.example.stockcryptotracker.view.details

import com.example.stockcryptotracker.dto.ChartData
import com.example.stockcryptotracker.dto.FinanceData

//tells presenter what to do
interface DetailsView {
    fun showError(errorMessage: String)
    fun bindChartData(data: ChartData)
    fun bindStockData(data: FinanceData)
}
