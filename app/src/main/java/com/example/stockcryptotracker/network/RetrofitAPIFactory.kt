package com.example.stockcryptotracker.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class RetrofitAPIFactory {
//    fun getYahooFinanceAPI(): YahooFinanceAPI {
//        return Retrofit.Builder()
//            .baseUrl("https://yfapi.net")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(YahooFinanceAPI::class.java)
//    }
//}