package com.example.stockcryptotracker.view.details

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.ChartData
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.view.home.HomeActivity
import com.example.stockcryptotracker.view.favorites.WatchlistActivity
import com.example.stockcryptotracker.view.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity(), DetailsView {

    val presenter = DetailsPresenter(this)
    lateinit var detailsContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        bindViews()
        presenter.start()

        title = "Details"

        // Bottom navigation bar
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
        //Log.d("asdf",data.quoteResponse.result[0].shortName)
    }

    private fun bindViews() {
        detailsContainer = findViewById(R.id.details_container)
    }

}