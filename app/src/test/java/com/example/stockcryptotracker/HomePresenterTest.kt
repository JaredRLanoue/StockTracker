package com.example.stockcryptotracker

import com.example.stockcryptotracker.service.YahooFinanceService
import com.example.stockcryptotracker.view.home.HomePresenter
import com.example.stockcryptotracker.view.home.HomeView
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*

class HomePresenterTest() {
    val view: HomeView = mockk(relaxed = true)
    val yahooService: YahooFinanceService = mockk(relaxed = true)
    var presenter = HomePresenter(view)

    @Test
    fun onStartCallGetMarketSummaryData() {
//        presenter.yahooService = yahooService

        presenter.start()

        verify { yahooService.getStockData(any(), any(), any()) }
    }

    @Test
    fun onStartCallGetMarketTrendingData() {
//        presenter.yahooService = yahooService

        presenter.start()

        verify { yahooService.getTrendingData(any(), any()) }
    }

}