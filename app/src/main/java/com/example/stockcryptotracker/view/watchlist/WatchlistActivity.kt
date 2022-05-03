package com.example.stockcryptotracker.view.watchlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.view.home.HomeActivity
import com.example.stockcryptotracker.view.home.StockAdapter
import com.example.stockcryptotracker.view.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class WatchlistActivity : AppCompatActivity(), WatchlistView {

    val presenter = WatchlistPresenter(this)
    lateinit var rvWatchlist: RecyclerView
    lateinit var watchlistContainer: View
    lateinit var tvWatchlistError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchlist)

        bindViews()
        presenter.start()

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

    override fun showError(errorMessage: String) {
        Snackbar.make(watchlistContainer, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun showEmptyWatchlistError(){
        tvWatchlistError.visibility = View.VISIBLE
    }

    override fun bindWatchlist(data: FinanceData) {
        rvWatchlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvWatchlist.adapter = StockAdapter(data)
    }

    private fun bindViews() {
        watchlistContainer = findViewById(R.id.watchlist_container)
        rvWatchlist = findViewById(R.id.rvWatchlist)
        tvWatchlistError = findViewById(R.id.watchlistError)
    }
}