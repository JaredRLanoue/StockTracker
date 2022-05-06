package com.example.stockcryptotracker

import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.FinanceData3
import com.example.stockcryptotracker.service.YahooFinanceService
import com.example.stockcryptotracker.view.home.HomePresenter
import com.example.stockcryptotracker.view.home.HomeView
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

import org.junit.Assert.*

class HomePresenterTest {
    val view: HomeView = mockk(relaxed = true)
    val yahooService: YahooFinanceService = mockk(relaxed = true)
    var presenter = HomePresenter(view, yahooService)

    @Test
    fun `On start, call market data`() {
        presenter.start()

        verify { yahooService.getMarketData(any(), any()) }
    }

    @Test
    fun `On start, call trending data`() {
        presenter.start()

        verify { yahooService.getTrendingData(any(), any()) }
    }
}
