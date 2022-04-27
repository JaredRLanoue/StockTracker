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

        // Still unsure if this is right, I grab the sharedpref using the presenter and use it below but is this other stuff fine since it's view logic?
        // I also grabbed the shared preferences set using the presenter? Still sorta lost on the mvp structure concept

        val id = intent.getStringExtra("id")!!
        val prefs = presenter.getSharedPref(applicationContext, id)
        val favoriteSet = presenter.getSharedPrefFavoritesSet(prefs)

        presenter.start(id)

        if (id in favoriteSet) {
            setFavoritesButton()
        }

        favoritesButton.setOnClickListener {
            if (favoritesButton.isChecked) {
                favoriteSet.add(id)
                with(prefs!!.edit()) {
                    putStringSet("id", favoriteSet.toMutableSet())
                    commit()
                }
            } else {
                favoriteSet.remove(id)
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
        stockPrice.text = stock.regularMarketPrice.toString()

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

    override fun setFavoritesButton() {
        favoritesButton.isChecked = true
    }

    private fun bindViews() {
        detailsContainer = findViewById(R.id.details_container)
        stockName = findViewById(R.id.stock_name)
        stockPrice = findViewById(R.id.stock_price)
        stockPercentChange = findViewById(R.id.stock_percent_change)
        favoritesButton = findViewById(R.id.favoritesButton)
    }

}