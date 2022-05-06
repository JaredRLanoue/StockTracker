package com.example.stockcryptotracker

import com.example.stockcryptotracker.service.YahooFinanceService
import com.example.stockcryptotracker.view.details.DetailsPresenter
import com.example.stockcryptotracker.view.details.DetailsView
import com.example.stockcryptotracker.view.home.HomePresenter
import com.example.stockcryptotracker.view.home.HomeView
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*

class DetailsPresenterTest {
    val view: DetailsView = mockk(relaxed = true)
    val yahooService: YahooFinanceService = mockk(relaxed = true)
    var presenter = DetailsPresenter(view, yahooService)
    val testID = "GOOGL"

    @Test
    fun testGetStockData() {
        presenter.start(testID)
        verify { yahooService.getStockData(testID, any(), any()) }
    }

    @Test
    fun testGetChartData() {
        presenter.start(testID)
        verify { yahooService.getChartData(testID, any(), any()) }
    }

}