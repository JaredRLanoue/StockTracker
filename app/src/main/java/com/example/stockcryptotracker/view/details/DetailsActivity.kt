package com.example.stockcryptotracker.view.details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.ChartData
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.StatsData
import com.example.stockcryptotracker.view.watchlist.WatchlistActivity
import com.example.stockcryptotracker.view.home.HomeActivity
import com.example.stockcryptotracker.view.home.StockApplication
import com.example.stockcryptotracker.view.search.SearchActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import java.text.NumberFormat
import kotlin.collections.ArrayList


class DetailsActivity : AppCompatActivity(), DetailsView {

    val presenter = DetailsPresenter(this)
    lateinit var detailsContainer: View
    lateinit var stockName: TextView
    lateinit var stockPrice: TextView
    lateinit var stockPercentChange: TextView
    lateinit var favoritesButton: ToggleButton
    lateinit var lineChart: LineChart
    lateinit var marketOpen: TextView
    lateinit var marketClose: TextView
    lateinit var volume: TextView
    lateinit var marketCap: TextView
    lateinit var fiftyTwoWeekLow: TextView
    lateinit var fiftyTwoWeekHigh: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        bindViews()

        // Not finished, don't put here - change to service
        val symbolID = intent.getStringExtra("id")!!

        // These two shouldn't call presenter, instead call service
        val favoriteSet = presenter.getSharedPrefFavoritesSet()

        presenter.start(symbolID)

        if (symbolID in favoriteSet) {
            favoritesButton.isChecked = true
        }

        favoritesButton.setOnClickListener {
            if (favoritesButton.isChecked) {
                favoriteSet.add(symbolID)
                with(
                    StockApplication.appContext.getSharedPreferences("watchlist", Context.MODE_PRIVATE
                    ).edit()
                ) {
                    putStringSet("watchlist", favoriteSet.toMutableSet())
                    commit()
                }
            }
            if (!favoritesButton.isChecked) {
                favoriteSet.remove(symbolID)
                with(
                    StockApplication.appContext.getSharedPreferences("watchlist", Context.MODE_PRIVATE
                    ).edit()
                ) {
                    putStringSet("watchlist", favoriteSet.toMutableSet())
                    commit()
                }
            }
        }

        title = "Details"

        // Bottom navigation bar, need to move still
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.ic_home
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.ic_search -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.ic_watchlist -> startActivity(Intent(this, WatchlistActivity::class.java))
            }
            true
        }
    }

    override fun showError(errorMessage: String) {
        Snackbar.make(detailsContainer, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun bindChartData(data: ChartData) {
        val price = data.chart.result[0].indicators.quote[0].close
        val timestamp = data.chart.result[0].timestamp
        val entries: ArrayList<Entry> = ArrayList()

        for (index in timestamp.indices) {
            if (timestamp[index].toString() !== "null") {
                if (price[index].toString() !== "null") {
                    entries.add(Entry(timestamp[index]!!.toFloat(), price[index]!!))
                }
            }
        }

        val lineDataSet = LineDataSet(entries, "")

        lineDataSet.apply {
            color = Color.parseColor("#00d964")
            setDrawCircles(false)
            setDrawHighlightIndicators(true)
            highLightColor = Color.parseColor("#ffffff")
            setDrawValues(false)
            lineWidth = 1.25f
        }

        lineChart.data = LineData(lineDataSet)

        lineChart.apply {
            setDrawGridBackground(false)
            setDrawBorders(false)
            description.isEnabled = false
            legend.isEnabled = false
            axisRight.isEnabled = false
            setExtraOffsets(15f, 15F, 30f, 15F)
        }

//         From what I found online using MPAndroidCharts, you can't set X/Y Axis titles. You can use a textview but
//         decided to just format the numbers so they are easier to understand using the two formatter classes I made.
//         These formatters are needed because you must pass a float into the entries, then you can format those values
//         later on using the valueFormatter.

        lineChart.axisLeft.apply {
            isEnabled = true
            textColor = Color.parseColor("#ffffff")
            textSize = 15f
            axisLineWidth = 1f
            setDrawGridLines(false)
            valueFormatter = PriceFormatter()
        }

        lineChart.xAxis.apply {
            isEnabled = true
            textSize = 15f
            axisLineWidth = 1f
            position = XAxis.XAxisPosition.BOTTOM
            textColor = Color.parseColor("#ffffff")
            setDrawGridLines(false)
            valueFormatter = DateFormatter()
            setLabelCount(6, true)
        }
        lineChart.invalidate()

    }

    @SuppressLint("SetTextI18n")
    override fun bindStockData(data: FinanceData) {
        val stock = data.quoteResponse.result[0]

        stockName.text = stock.shortName

        if (stock.regularMarketPrice >= 0.01) {
            stockPrice.text = String.format("%.2f", stock.regularMarketPrice)
        } else {
            stockPrice.text = String.format("%.6f", stock.regularMarketPrice.toBigDecimal())
        }

        val roundedPercentChange =
            String.format("%.2f", stock.regularMarketChangePercent)

        if (stock.regularMarketChangePercent >= 0) {
            stockPercentChange.setTextColor(Color.parseColor("#00D964"))
            stockPercentChange.text = "+$roundedPercentChange%"
        } else {
            stockPercentChange.setTextColor(Color.parseColor("#FC0000"))
            stockPercentChange.text = "$roundedPercentChange%"
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindStatsData(data: StatsData) {
        marketCap.text = "Mkt Cap: ${data.quoteSummary.result[0].summaryDetail.marketCap?.fmt}"
        marketOpen.text = "Open: ${data.quoteSummary.result[0].summaryDetail.regularMarketOpen?.fmt}"
        marketClose.text = "Close: ${data.quoteSummary.result[0].summaryDetail.regularMarketPreviousClose?.fmt}"
        volume.text = "Volume: ${data.quoteSummary.result[0].summaryDetail.volume?.fmt}"
        fiftyTwoWeekHigh.text = "52Wk Hi: ${data.quoteSummary.result[0].summaryDetail.fiftyTwoWeekHigh?.fmt}"
        fiftyTwoWeekLow.text = "52Wk Lo: ${data.quoteSummary.result[0].summaryDetail.fiftyTwoWeekLow?.fmt}"

    }

    private fun bindViews() {
        detailsContainer = findViewById(R.id.details_container)
        stockName = findViewById(R.id.stock_name)
        stockPrice = findViewById(R.id.stock_price)
        stockPercentChange = findViewById(R.id.stock_percent_change)
        favoritesButton = findViewById(R.id.favoritesButton)
        lineChart = findViewById(R.id.line_chart)
        marketOpen = findViewById(R.id.open)
        marketClose = findViewById(R.id.close)
        volume = findViewById(R.id.volume)
        marketCap = findViewById(R.id.marketcap)
        fiftyTwoWeekHigh = findViewById(R.id.fiftyHigh)
        fiftyTwoWeekLow = findViewById(R.id.fiftyLow)

    }

}