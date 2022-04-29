package com.example.stockcryptotracker.view.favorites

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.view.home.HomeActivity
import com.example.stockcryptotracker.view.home.MarketSummaryAdapter
import com.example.stockcryptotracker.view.home.TrendingAdapter
import com.example.stockcryptotracker.view.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class WatchlistActivity : AppCompatActivity(), WatchlistView {

    val presenter = WatchlistPresenter(this)
    lateinit var rvWatchlist: RecyclerView
    lateinit var watchlistContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)


        val prefs = getSharedPreferences("id", Context.MODE_PRIVATE) // Use presenter to grab shared preferences
        val fetch: MutableSet<String>? = prefs.getStringSet("id", null)
        Log.d("asdf", fetch.toString()) // Need to implement this still, currently only logs the watchlist. (Might change to favorites list instead)
        title = "Watchlist"
        bindViews()
        presenter.start(fetch)


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
    override fun showError(errorMessage: String) {
        Snackbar.make(watchlistContainer, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun bindWatchlist(data: FinanceData) {
        rvWatchlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvWatchlist.adapter = TrendingAdapter(data)
    }

    private fun bindViews() {
        watchlistContainer = findViewById(R.id.watchlist_container)
        rvWatchlist = findViewById(R.id.rvWatchlist)
    }
}