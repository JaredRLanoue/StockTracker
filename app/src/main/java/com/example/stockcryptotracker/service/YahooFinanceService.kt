package com.example.stockcryptotracker.service

import android.util.Log
import com.example.stockcryptotracker.dto.*
import com.example.stockcryptotracker.network.RetrofitAPIFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YahooFinanceService {

    private val api = RetrofitAPIFactory().getYahooFinanceAPI()

    fun getStockData(symbols: String) {
        api.getStockDetails(symbols).enqueue(object : Callback<FinanceData> {
            override fun onResponse(call: Call<FinanceData>, response: Response<FinanceData>) {
                Log.d("asdf", response.body()!!.toString())
                //showData(response.body()!!.quoteResponse.result)
            }

            override fun onFailure(call: Call<FinanceData>, t: Throwable) {
                Log.d("asdf", t.toString())
            }
        })
    }


    fun getTrendingData() {
        api.getTrendingDetails().enqueue(object : Callback<TrendingData> {
            override fun onResponse(call: Call<TrendingData>, response: Response<TrendingData>) {
                val top10TrendingSymbols = response.body()!!.finance.result[0]
                showData(top10TrendingSymbols)
            }

            override fun onFailure(call: Call<TrendingData>, t: Throwable) {
                Log.d("asdf", t.toString())
            }
        })
    }

    fun showData(data: TrendingData3) {
        Log.d("asdf", data.toString())
        val listOfTrendingSymbols: MutableList<String> = mutableListOf()

        for (i in 0..9) {
            listOfTrendingSymbols += data.quotes[i].symbol
        }

        val trendingSymbolsNoSpaces = listOfTrendingSymbols.joinToString().filter{!it.isWhitespace()} //can send this to getStockData to get details, then grab each symbols details and put it into recyclerview that is clickable to new page of data.
        Log.d("asdf", trendingSymbolsNoSpaces)
    }

            // Figure out homeview/homepresenter.
            // Pass through getStockDetails by using query to get name/percentage instead of just ticker?
            // Literally just put a comma between the symbols, no special letters or characters.
            // Create a comma seperated list of these symbols then pass them through the getStockDetails API, then create recyclerview from this data
            // that is clickable to get to details page with the data already obtained.
}
