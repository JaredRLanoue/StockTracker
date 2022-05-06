package com.example.stockcryptotracker.view.details

import com.example.stockcryptotracker.dto.ChartData
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.StatsData
import com.github.mikephil.charting.data.LineDataSet


interface DetailsView {
    fun showError(errorMessage: String)
    fun bindChartData(data: LineDataSet)
    fun bindStockData(data: FinanceData)
    fun bindStatsData(data: StatsData)
}
