package com.example.stockcryptotracker.network

import com.example.stockcryptotracker.dto.FinanceData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface YahooFinanceAPI {
    @Headers("accept: application/json", "x-api-key: JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd")
    @GET("v6/finance/quote?region=US&lang=en&symbols=QQQ")
    fun getYahooDetails(): Call<FinanceData>
}