package com.example.stockcryptotracker.view.favorites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.view.home.HomeActivity
import com.example.stockcryptotracker.view.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class WatchlistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val prefs = getSharedPreferences("id", Context.MODE_PRIVATE) // Use presenter to grab shared preferences
        val fetch: Set<String> = prefs.getStringSet("id", HashSet()) as Set<String>
        Log.d("asdf", fetch.toString()) // Need to implement this still, currently only logs the watchlist. (Might change to favorites list instead)

        title = "Watchlist"

        // Bottom navigation bar, need to move still
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