package com.example.stockcryptotracker.service

import android.util.Log
import com.example.stockcryptotracker.dto.*
import com.example.stockcryptotracker.network.RetrofitAPIFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YahooFinanceService {

    private val api = RetrofitAPIFactory().getYahooFinanceAPI()

    fun getStockData(symbols: String, successCallback: (FinanceData) -> Unit, failureCallback: (errorMessage: String) -> Unit) {
        api.getStockDetails(symbols).enqueue(object : Callback<FinanceData> {
            override fun onResponse(call: Call<FinanceData>, response: Response<FinanceData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        successCallback(it)
                    } ?: run {
                        failureCallback("No jokes returned from service")
                    }
                } else {
                    failureCallback("Error getting jokes")
                }
            }
                //showData(response.body()!!.quoteResponse.result)

            override fun onFailure(call: Call<FinanceData>, t: Throwable) {
                failureCallback("Error: ${t.message}")
            }
        })
    }






//    fun getTrendingData() {
//        api.getTrendingDetails().enqueue(object : Callback<TrendingData> {
//            override fun onResponse(call: Call<TrendingData>, response: Response<TrendingData>) {
//                val top10TrendingSymbols = response.body()!!.finance.result[0]
//                trendingDataToString(top10TrendingSymbols)
//            }
//
//            override fun onFailure(call: Call<TrendingData>, t: Throwable) {
//                Log.d("asdf", t.toString())
//            }
//        })
//    }
}




    // Testing functions to see output, should close the above class here and not include the following functions
//    fun trendingDataToString(data: TrendingData3) {
//        val listOfTrendingSymbols: MutableList<String> = mutableListOf()
//        for (i in 0..9) {
//            listOfTrendingSymbols += data.quotes[i].symbol
//        }
//
//        val trendingSymbolsNoSpaces = listOfTrendingSymbols.joinToString()
//            .filter { !it.isWhitespace() }
//        YahooFinanceService().getStockData(trendingSymbolsNoSpaces)
//    }
//
//    fun showData(data: List<FinanceData3>) {
//        for (element in data) {
//            Log.d("asdf", element.toString())
//        }
//    }


            // Figure out homeview/homepresenter.
            // Pass through getStockDetails by using query to get name/percentage instead of just ticker?
            // Literally just put a comma between the symbols, no special letters or characters.
            // Create a comma seperated list of these symbols then pass them through the getStockDetails API, then create recyclerview from this data
            // that is clickable to get to details page with the data already obtained.

