package com.example.stockcryptotracker.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.FinanceData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class HomeActivity : AppCompatActivity(), HomeView {

    val presenter = HomePresenter(this)
    lateinit var rvUSMarkets: RecyclerView
    lateinit var homeContainer: View


    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bindViews()
        presenter.start()




        // Title named home and subtitle with date, put computing data into presenter and the setting of titlebar into view. Call it from this activity.
        val calender = Calendar.getInstance()
        val currentMonth = SimpleDateFormat("MMMM").format(calender.time)
        val currentDay = SimpleDateFormat("d").format(calender.time)
        supportActionBar?.title = "Home"
        supportActionBar?.subtitle = "$currentMonth $currentDay"

        //val usMarkets = YahooFinanceService().getStockData("^IXIC,^DJI,^GSPC")
        //val finance2 = YahooFinanceService().getTrendingData()
        // Bottom navigation bar
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.ic_home
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.ic_search -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.ic_news -> startActivity(Intent(this, NewsActivity::class.java))
            }
            true
        }


    }

    override fun showError(errorMessage: String) {
        Snackbar.make(homeContainer, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun bindUSMarket(data: FinanceData) {
        rvUSMarkets.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvUSMarkets.adapter = USMarketAdapter(data)
    }

    private fun bindViews() {
        homeContainer = findViewById(R.id.home_container)
        rvUSMarkets = findViewById(R.id.rvUSMarkets)
    }


}