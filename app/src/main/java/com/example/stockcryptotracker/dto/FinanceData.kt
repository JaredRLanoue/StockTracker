package com.example.stockcryptotracker.dto

data class FinanceData(
    val quoteResponse: FinanceData2
)

data class FinanceData2(
    val result: List<FinanceData3>
)

data class FinanceData3(
    val shortName: String,
    val regularMarketPrice: Float,
    val regularMarketChangePercent: Float,
    val fullExchangeName: String?,
    val symbol: String? = "-" // Work on setting default value similar to this, doesn't work because of GSON?
)