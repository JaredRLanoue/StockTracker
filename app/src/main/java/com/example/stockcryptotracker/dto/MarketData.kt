package com.example.stockcryptotracker.dto

data class MarketData(
    val marketSummaryResponse: MarketData2
)

data class MarketData2(
    val result: List<MarketData3>
)

data class MarketData3(
    val shortName: String,
    val regularMarketChangePercent: MarketData4,
    val regularMarketPrice: MarketData5
)

data class MarketData4(
    val fmt: Int
)

data class MarketData5(
    val fmt: Int
)