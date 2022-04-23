package com.example.stockcryptotracker.view.favorites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.view.home.HomeActivity
import com.example.stockcryptotracker.view.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class WatchlistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        title = "Watchlist"

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.ic_watchlist
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.ic_search -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.ic_watchlist -> startActivity(Intent(this, WatchlistActivity::class.java))
            }
            true
        }
    }
}