package com.example.stockcryptotracker

import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.FinanceData2
import com.example.stockcryptotracker.dto.FinanceData3
import com.example.stockcryptotracker.service.YahooFinanceService
import com.example.stockcryptotracker.view.home.HomePresenter
import com.example.stockcryptotracker.view.home.HomeView
import com.example.stockcryptotracker.view.watchlist.WatchlistPresenter
import com.example.stockcryptotracker.view.watchlist.WatchlistView
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class WatchlistPresenterTest {

    val view: WatchlistView = mockk(relaxed = true)
    val yahooService: YahooFinanceService = mockk(relaxed = true)
    var presenter = WatchlistPresenter(view, yahooService)
    val testID = "GOOGL"

    @Test
    fun `After getting finance data if success then bind to view`() {
        val data = buildFinanceData()

        presenter.start()

        every { yahooService.getStockData(testID, any(), any()) } answers {
            firstArg<(FinanceData) -> Unit>().invoke(data)

            verify { view.bindWatchlist(data) }
            verify { view.showError("Error!") }
            verify { view.showEmptyWatchlistError() }
        }
    }

    fun buildFinanceData(): FinanceData {
        return FinanceData(
            FinanceData2(
                listOf(
                    FinanceData3(
                        displayName = "Google",
                        typeDisp = "Equity",
                        fullExchangeName = "NASDAQGS",
                        shortName = "Alphabet Inc.",
                        regularMarketPrice = 2300.toFloat(),
                        regularMarketChangePercent = (-1.2922182).toFloat(),
                        symbol = "GOOGL",
                        fiftyTwoWeekHigh = 3030.93.toFloat(),
                        fiftyTwoWeekLow = 2193.62.toFloat(),
                        marketCap = 1516040486912.toFloat(),
                    )
                )
            )
        )
    }
}