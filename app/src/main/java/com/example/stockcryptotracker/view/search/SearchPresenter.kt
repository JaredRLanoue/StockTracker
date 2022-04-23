package com.example.stockcryptotracker.view.search

import android.util.Log
import com.example.stockcryptotracker.service.YahooFinanceService


class SearchPresenter(val view: SearchView) {
    val yahooService = YahooFinanceService()

    fun start(parameter: String) {
        getSearchData(parameter)
    }

    fun getSearchData(parameter: String) {
        yahooService.getSearchData(
            parameter,
            successCallback = { data ->
                view.bindSearchData(data)
            },
            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }
}

