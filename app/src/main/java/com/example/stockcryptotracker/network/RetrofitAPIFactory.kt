package com.example.stockcryptotracker.network

import com.example.stockcryptotracker.service.YahooFinanceService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPIFactory {

    val keys = listOf( "7QJ92b1BU15ve09x4GBux1dncqQOrswA6vxnRn1y",
            "JnCqeBCPiQ1MYSdh1ac5K2OXQAefqKAJ21grOZsd",
            "y1v2pG1WQ15gSlMi6RCVY5AVDTB77nBv7XQWeDXs",
            "x6Dddp50Bq77LskF9J5xga83TZ9AKj9I3Jge2aAN",
            "xacrcepaPz2nRRxJAFu3s3eT734mu5vpnsYt5z22",
            "CAvlodXQUv7xj7THqw4ZS9fjgSMcHpsJ6BUOePkF",
            "lJTACacGb94rHFBh64Fca270eqNpjPyia4TrhGHI",
            "UoTC5JNyX38bf8lcjBf2o8zC3ajDPQRh3fWTVHQO",
            "pYKNlsn95o2SmywxoNzV95JZqjJKl0vL8GPq7llf",
            "rlBWygQn5E5CH5FirlIoBj6NA7I2bSp1RaInPcHe",
            "l6jwGC5zbl1WyQXLZXBAB7X6yhEznj2N7BYrZwDG",
            "3sMNfGc1eg4mHNFCDExue5zhUpKwNsJE9lLqnQnc",
            "70KHyCk5dZ8RVZzHLEYCc5U0Ro4zV0ni7KoJbFFv",
            "hONdj07Kwr3A0UbyK1K7L1m1ePsHKEUS3htNIb4s",
            "YvaQQOKRfZ6Rozzku8kBxnHczZslJxx5two4tly6",
            "Q76p2aDunwac4BQrywXq05FF8X77RLN02HGIdpLr",
            "Iy5D9N3w1n9yioJ1RgNNd5jBypjYV5DO50IPp33Z",
            "WP7mJoBOlT5VdZzTgHfzx6Obr3RyMWQy7KRH7O2s")

    // Wanted to see if I can loop through the keys but decided to leave it as is, just need to change keys index if response fails (0-17 are valid indexes)
    // These keys are also all free accounts so I don't care if they get stolen or anything so leaving them here on while GitHub is fine :)

    fun getYahooFinanceAPI(): YahooFinanceAPI {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", keys[1])
                chain.proceed(request.build())
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://yfapi.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(YahooFinanceAPI::class.java)
    }
}