package com.example.stockcryptotracker.view.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.SearchData
import com.example.stockcryptotracker.view.home.HomeActivity
import com.example.stockcryptotracker.view.favorites.WatchlistActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class SearchActivity : AppCompatActivity(), SearchView {

    val presenter = SearchPresenter(this)
    lateinit var searchContainer: View
    lateinit var searchBar: EditText
    lateinit var rvSearch: RecyclerView
    lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        bindViews()

        // not sure if this is right in terms of mvp?
        searchButton.setOnClickListener {
            val parameter = searchBar.text.toString()
            if(parameter.isNotEmpty()) {
                presenter.start(parameter)
            }
        }

        title = "Search"

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.ic_search
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
        Snackbar.make(searchContainer, errorMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun bindSearchData(data: SearchData) {
        rvSearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvSearch.adapter = SearchAdapter(data)
    }

    private fun bindViews() {
        searchContainer = findViewById(R.id.search_container)
        searchBar = findViewById(R.id.search_bar)
        searchButton = findViewById(R.id.search_button)
        rvSearch = findViewById(R.id.rvSearch)
    }

}