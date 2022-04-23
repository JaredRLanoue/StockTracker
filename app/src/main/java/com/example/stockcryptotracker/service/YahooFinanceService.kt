package com.example.stockcryptotracker.service

import android.util.Log
import com.example.stockcryptotracker.dto.*
import com.example.stockcryptotracker.network.RetrofitAPIFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YahooFinanceService {

    private val api = RetrofitAPIFactory().getYahooFinanceAPI()

    fun getStockData(
        symbols: String,
        successCallback: (FinanceData) -> Unit,
        failureCallback: (errorMessage: String) -> Unit
    ) {
        api.getStockDetails(symbols).enqueue(object : Callback<FinanceData> {
            override fun onResponse(call: Call<FinanceData>, response: Response<FinanceData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        successCallback(it)
                    } ?: run {
                        failureCallback("Error getting market data, please try again!")
                    }
                } else {
                    failureCallback("Error getting response from server, please try again!")
                }
            }

            override fun onFailure(call: Call<FinanceData>, t: Throwable) {
                failureCallback("Error: ${t.message}")
            }
        })
    }

    fun getTrendingData(
        successCallback: (TrendingData) -> Unit,
        failureCallback: (errorMessage: String) -> Unit
    ) {
        api.getTrendingDetails().enqueue(object : Callback<TrendingData> {
            override fun onResponse(call: Call<TrendingData>, response: Response<TrendingData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        successCallback(it)
                    } ?: run {
                        failureCallback("Error getting trending data, please try again!")
                    }
                } else {
                    failureCallback("Error getting response from server, please try again!")
                }
            }

            override fun onFailure(call: Call<TrendingData>, t: Throwable) {
                failureCallback("Error: ${t.message}")
            }
        })
    }

    fun getChartData(
        successCallback: (ChartData) -> Unit,
        failureCallback: (errorMessage: String) -> Unit
    ) {
        api.getChartDetails().enqueue(object : Callback<ChartData> {
            override fun onResponse(call: Call<ChartData>, response: Response<ChartData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        successCallback(it)
                    } ?: run {
                        failureCallback("Error getting trending data, please try again!")
                    }
                } else {
                    failureCallback("Error getting response from server, please try again!")
                }
            }

            override fun onFailure(call: Call<ChartData>, t: Throwable) {
                failureCallback("Error: ${t.message}")
            }
        })
    }

    fun getSearchData(
        parameter: String,
        successCallback: (SearchData) -> Unit,
        failureCallback: (errorMessage: String) -> Unit
    ) {
        api.getSearchDetails(parameter).enqueue(object : Callback<SearchData> {
            override fun onResponse(call: Call<SearchData>, response: Response<SearchData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        successCallback(it)
                    } ?: run {
                        failureCallback("Error getting search data, please try again!")
                    }
                } else {
                    failureCallback("Error getting response from server, please try again!")
                }
            }

            override fun onFailure(call: Call<SearchData>, t: Throwable) {
                failureCallback("Error: ${t.message}")
            }
        })
    }

}

