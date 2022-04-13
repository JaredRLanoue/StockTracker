package com.example.stockcryptotracker.service

import android.util.Log
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.network.RetrofitAPIFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YahooFinanceService {

    val api = RetrofitAPIFactory().getYahooFinanceAPI()

    fun getFinanceData() {
        api.getYahooDetails().enqueue(object : Callback<FinanceData> {
            override fun onResponse(call: Call<FinanceData>, response: Response<FinanceData>) {
                Log.d("asdf", response.body().toString())
            }

            override fun onFailure(call: Call<FinanceData>, t: Throwable) {
                Log.d("asdf", t.toString())
            }
        })
    }
}