package com.example.stockcryptotracker.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.service.YahooFinanceService
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Title named home and subtitle with date, put computing data into presenter and the setting of titlebar into view. Call it from this activity.
        val calender = Calendar.getInstance()
        val currentMonth = SimpleDateFormat("MMMM").format(calender.time)
        val currentDay = SimpleDateFormat("d").format(calender.time)
        supportActionBar?.title = "Home"
        supportActionBar?.subtitle = "$currentMonth $currentDay"

        //val finance = YahooFinanceService().getStockData()
        val finance2 = YahooFinanceService().getTrendingData()

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
}