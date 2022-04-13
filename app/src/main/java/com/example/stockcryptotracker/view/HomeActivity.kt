package com.example.stockcryptotracker.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.network.RetrofitAPIFactory
import com.example.stockcryptotracker.service.YahooFinanceService
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Title named home and subtitle with date
        val cal = Calendar.getInstance()
        val curMonth = SimpleDateFormat("MMMM").format(cal.time)
        val curDay = SimpleDateFormat("d").format(cal.time)
        supportActionBar?.title = "Home"
        supportActionBar?.subtitle = "$curMonth $curDay"

        val finance = YahooFinanceService().getFinanceData()


//        val api = RetrofitAPIFactory().getYahooFinanceAPI()
//        api.getYahooDetails().enqueue(object : Callback<FinanceData> {
//            override fun onResponse(call: Call<FinanceData>, response: Response<FinanceData>) {
//                Log.d("asdf", response.body().toString())
//            }
//            override fun onFailure(call: Call<FinanceData>, t: Throwable) {
//                Log.d("asdf", t.toString())
//            }
//        })






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