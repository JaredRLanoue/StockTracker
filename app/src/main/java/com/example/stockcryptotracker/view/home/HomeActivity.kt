package com.example.stockcryptotracker.view.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.MarketData
import com.example.stockcryptotracker.service.YahooFinanceService
import com.example.stockcryptotracker.view.watchlist.WatchlistActivity
import com.example.stockcryptotracker.view.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class HomeActivity : AppCompatActivity(), HomeView {

    val presenter = HomePresenter(this, yahooService = YahooFinanceService())
    lateinit var rvUSMarkets: RecyclerView
    lateinit var rvTrending: RecyclerView
    lateinit var homeContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUpTitle()
        setUpNav()
        bindViews()
        presenter.start()

    }

    private fun setUpNav(){
        // This helps make the activity switching/nav bar less abrupt, didn't get a chance to add Fragments yet
        overridePendingTransition(androidx.transition.R.anim.abc_fade_in, androidx.transition.R.anim.abc_fade_out)

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

    @SuppressLint("SimpleDateFormat")
    fun setUpTitle(){
        val calender = Calendar.getInstance()
        val currentMonth = SimpleDateFormat("MMMM").format(calender.time)
        val currentDay = SimpleDateFormat("d").format(calender.time)
        supportActionBar?.title = "Home"
        supportActionBar?.subtitle = "$currentMonth $currentDay"
    }

    override fun showError(errorMessage: String) {
        Snackbar.make(homeContainer, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun bindMarketSummary(data: MarketData) {
        rvUSMarkets.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvUSMarkets.adapter = MarketSummaryAdapter(data)
    }

    private fun bindViews() {
        homeContainer = findViewById(R.id.home_container)
        rvUSMarkets = findViewById(R.id.rvUSMarkets)
        rvTrending = findViewById(R.id.rvTrending)
    }

    override fun bindTrending(data: FinanceData) {
        rvTrending.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTrending.adapter = StockAdapter(data)
    }

}