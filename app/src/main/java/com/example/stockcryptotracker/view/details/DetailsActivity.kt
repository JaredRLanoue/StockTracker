package com.example.stockcryptotracker.view.details

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.ChartData
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.view.favorites.WatchlistActivity
import com.example.stockcryptotracker.view.home.HomeActivity
import com.example.stockcryptotracker.view.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class DetailsActivity : AppCompatActivity(), DetailsView {


    val presenter = DetailsPresenter(this)
    lateinit var detailsContainer: View
    lateinit var stockName: TextView
    lateinit var stockPrice: TextView
    lateinit var stockPercentChange: TextView
    lateinit var favoritesButton: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        bindViews()

        // not finished, dont put here. change to service/mvp layout
        val symbolID = intent.getStringExtra("id")!!

        // these two shouldnt call presenter, instead call service
        val prefs = presenter.getSharedPref(applicationContext, symbolID)
        val favoriteSet = presenter.getSharedPrefFavoritesSet(prefs)

        presenter.start(symbolID)

        // unsure how to do these
        if (symbolID in favoriteSet) {
            favoritesButton.isChecked = true
        }

        favoritesButton.setOnClickListener {
            if (favoritesButton.isChecked) {
                favoriteSet.add(symbolID)
                with(prefs!!.edit()) {
                    putStringSet("id", favoriteSet.toMutableSet())
                    commit()
                }
            } else {
                favoriteSet.remove(symbolID)
                with(prefs!!.edit()) {
                    putStringSet("id", favoriteSet.toMutableSet())
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
        //Log.d("asdf",data.chart.result[0].indicators.quote.toString())
        //Log.d("asdf",data.chart.result[0].timestamp.toString())
    }

    override fun bindStockData(data: FinanceData) {
        val stock = data.quoteResponse.result[0]
        stockName.text = stock.shortName
        stockPrice.text = String.format("%.2f", stock.regularMarketPrice)

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

    private fun bindViews() {
        detailsContainer = findViewById(R.id.details_container)
        stockName = findViewById(R.id.stock_name)
        stockPrice = findViewById(R.id.stock_price)
        stockPercentChange = findViewById(R.id.stock_percent_change)
        favoritesButton = findViewById(R.id.favoritesButton)
    }

}