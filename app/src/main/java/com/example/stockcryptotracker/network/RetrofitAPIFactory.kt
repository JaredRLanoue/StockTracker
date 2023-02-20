package com.example.stockcryptotracker.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitAPIFactory {

    private val apiKey = "Enter API Key Here From https://financeapi.net/"

    fun getYahooFinanceAPI(): YahooFinanceAPI {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-api-key", apiKey)
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
