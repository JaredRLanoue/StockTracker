package com.example.stockcryptotracker.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FinanceData(
    val quoteResponse: FinanceData2
)

data class FinanceData2(
    val result: List<FinanceData3>
)

data class FinanceData3(
    val shortName: String,
    val regularMarketPrice: Float,
    val regularMarketChangePercent: Float
)