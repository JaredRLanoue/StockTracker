package com.example.stockcryptotracker.view.details

import android.content.Intent
import com.example.stockcryptotracker.service.YahooFinanceService
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.example.stockcryptotracker.dto.ChartData


class DetailsPresenter(val view: DetailsView, val yahooService: YahooFinanceService) {

    fun start(id: String) {
        getStockData(id)
        getChartData(id)
        getStatsData(id)
    }

    fun getStockData(id: String) {
        yahooService.getStockData(
            id,
            successCallback = { data ->
                view.bindStockData(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

    fun getChartData(id: String) {
        yahooService.getChartData(
            id,
            successCallback = { data ->
                view.bindChartData(collectChartData(data))
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

    fun getStatsData(id: String) {
        yahooService.getStatsData(
            id,
            successCallback = { data ->
                view.bindStatsData(data)
            },

            failureCallback = { errorMessage ->
                view.showError(errorMessage)
            }
        )
    }

    fun collectChartData(data: ChartData): LineDataSet {
        val price = data.chart.result[0].indicators.quote[0].close
        val timestamp = data.chart.result[0].timestamp
        val entries: ArrayList<Entry> = ArrayList()

        for (index in timestamp.indices) {
            if (timestamp[index].toString() !== "null" && price[index].toString() !== "null") {
                    entries.add(Entry(timestamp[index]!!.toFloat(), price[index]!!))
            }
        }

        val lineDataSet = LineDataSet(entries, "")
        return lineDataSet
    }
}

