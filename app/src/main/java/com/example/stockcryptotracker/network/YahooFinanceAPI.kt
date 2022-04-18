package com.example.stockcryptotracker.network

import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.TrendingData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YahooFinanceAPI {

    @Headers("accept: application/json", "x-api-key: 7QJ92b1BU15ve09x4GBux1dncqQOrswA6vxnRn1y")
    @GET("v6/finance/quote?region=US&lang=en&")
    fun getStockDetails(@Query("symbols")symbols: String): Call<FinanceData>

    @Headers("accept: application/json", "x-api-key: 7QJ92b1BU15ve09x4GBux1dncqQOrswA6vxnRn1y")
    @GET("v1/finance/trending/US")
    fun getTrendingDetails(): Call<TrendingData>


}


// first api key: 7QJ92b1BU15ve09x4GBux1dncqQOrswA6vxnRn1y
// second api key: JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd