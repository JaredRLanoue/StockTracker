package com.example.stockcryptotracker.network

import com.example.stockcryptotracker.dto.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface YahooFinanceAPI {

    @Headers("accept: application/json", "x-api-key: WP7mJoBOlT5VdZzTgHfzx6Obr3RyMWQy7KRH7O2s")
    @GET("v6/finance/quote?region=US&lang=en&")
    fun getStockDetails(@Query("symbols")symbols: String): Call<FinanceData>

    @Headers("accept: application/json", "x-api-key: WP7mJoBOlT5VdZzTgHfzx6Obr3RyMWQy7KRH7O2s")
    @GET("v1/finance/trending/US")
    fun getTrendingDetails(): Call<TrendingData>

    @Headers("accept: application/json", "x-api-key: WP7mJoBOlT5VdZzTgHfzx6Obr3RyMWQy7KRH7O2s")
    @GET("v8/finance/chart/{symbol}?range=max&region=US&interval=1mo&lang=en&events=div%2Csplit")
    fun getChartDetails(@Path("symbol")symbol: String): Call<ChartData>

    @Headers("accept: application/json", "x-api-key: WP7mJoBOlT5VdZzTgHfzx6Obr3RyMWQy7KRH7O2s")
    @GET("v6/finance/autocomplete?region=US&lang=en&")
    fun getSearchDetails(@Query("query")symbol: String): Call<SearchData>

    @Headers("accept: application/json", "x-api-key: WP7mJoBOlT5VdZzTgHfzx6Obr3RyMWQy7KRH7O2s")
    @GET("v6/finance/quote/marketSummary")
    fun getMarketSummaryDetails(): Call<MarketData>

    @Headers("accept: application/json", "x-api-key: WP7mJoBOlT5VdZzTgHfzx6Obr3RyMWQy7KRH7O2s")
    @GET("v11/finance/quoteSummary/{symbol}?lang=en&region=US&modules=summaryDetail")
    fun getStockStatsDetails(@Path("symbol")symbol: String):Call<StatsData>


}

// 7QJ92b1BU15ve09x4GBux1dncqQOrswA6vxnRn1y
// JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd
// y1v2pG1WQ15gSlMi6RCVY5AVDTB77nBv7XQWeDXs
// x6Dddp50Bq77LskF9J5xga83TZ9AKj9I3Jge2aAN
// xacrcepaPz2nRRxJAFu3s3eT734mu5vpnsYt5z22
// CAvlodXQUv7xj7THqw4ZS9fjgSMcHpsJ6BUOePkF
// lJTACacGb94rHFBh64Fca270eqNpjPyia4TrhGHI
// UoTC5JNyX38bf8lcjBf2o8zC3ajDPQRh3fWTVHQO
// pYKNlsn95o2SmywxoNzV95JZqjJKl0vL8GPq7llf
// rlBWygQn5E5CH5FirlIoBj6NA7I2bSp1RaInPcHe
// l6jwGC5zbl1WyQXLZXBAB7X6yhEznj2N7BYrZwDG
// 3sMNfGc1eg4mHNFCDExue5zhUpKwNsJE9lLqnQnc
// 70KHyCk5dZ8RVZzHLEYCc5U0Ro4zV0ni7KoJbFFv
// hONdj07Kwr3A0UbyK1K7L1m1ePsHKEUS3htNIb4s
// YvaQQOKRfZ6Rozzku8kBxnHczZslJxx5two4tly6
// Q76p2aDunwac4BQrywXq05FF8X77RLN02HGIdpLr
// Iy5D9N3w1n9yioJ1RgNNd5jBypjYV5DO50IPp33Z
// WP7mJoBOlT5VdZzTgHfzx6Obr3RyMWQy7KRH7O2s