package com.example.stockcryptotracker.view.details

import android.annotation.SuppressLint
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter : IndexAxisValueFormatter() {

    @SuppressLint("SimpleDateFormat")
    override fun getFormattedValue(value: Float): String? {
        val results = value.toLong() * 1000
        return SimpleDateFormat("yyyy").format(results)
    }
}