package com.example.stockcryptotracker.network

import com.example.stockcryptotracker.dto.ChartData
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.TrendingData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface YahooFinanceAPI {

    @Headers("accept: application/json", "x-api-key: JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd")
    @GET("v6/finance/quote?region=US&lang=en&")
    fun getStockDetails(@Query("symbols")symbols: String): Call<FinanceData>

    @Headers("accept: application/json", "x-api-key: JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd")
    @GET("v6/finance/quote/marketSummary?lang=en&region=US")
    fun getMarketSummary(): Call<FinanceData>

    @Headers("accept: application/json", "x-api-key: JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd")
    @GET("v1/finance/trending/US")
    fun getTrendingDetails(): Call<TrendingData>

    @Headers("accept: application/json", "x-api-key: JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd")
    @GET("v8/finance/chart/")
    fun getChartDetails(@Query("")symbol: String): Call<ChartData>

}


// first api key: 7QJ92b1BU15ve09x4GBux1dncqQOrswA6vxnRn1y
// second api key: JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd
// third api key: y1v2pG1WQ15gSlMi6RCVY5AVDTB77nBv7XQWeDXs
// fourth api key: x6Dddp50Bq77LskF9J5xga83TZ9AKj9I3Jge2aAN
// fifth api key: xacrcepaPz2nRRxJAFu3s3eT734mu5vpnsYt5z22
// sixth api key: CAvlodXQUv7xj7THqw4ZS9fjgSMcHpsJ6BUOePkF
// seventh api key: lJTACacGb94rHFBh64Fca270eqNpjPyia4TrhGHI