package com.example.stockcryptotracker.view.search

import com.example.stockcryptotracker.dto.ChartData
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.SearchData

interface SearchView {
    fun showError(errorMessage: String)
    fun bindSearchData(data: SearchData)
}
