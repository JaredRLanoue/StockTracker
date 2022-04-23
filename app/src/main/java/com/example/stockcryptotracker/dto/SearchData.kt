package com.example.stockcryptotracker.dto

data class SearchData(
    val ResultSet: SearchData2
)

data class SearchData2(
    val Result: List<SearchData3>
)

data class SearchData3(
    val symbol: String?,
    val name: String?
)
