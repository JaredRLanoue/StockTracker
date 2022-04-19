package com.example.stockcryptotracker.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.FinanceData3

class USMarketAdapter(private val data: FinanceData) : RecyclerView.Adapter<USMarketAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.stock_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = data.quoteResponse.result[position].shortName
        holder.tvPrice.text = String.format("%.2f", data.quoteResponse.result[position].regularMarketPrice)

        val roundedPercentChange = String.format("%.2f", data.quoteResponse.result[position].regularMarketChangePercent)

        if (data.quoteResponse.result[position].regularMarketChangePercent >= 0){
            holder.tvPercentChange.setTextColor(Color.parseColor("#00D964"))
            holder.tvPercentChange.text = "+$roundedPercentChange%"
        }
        else {
            holder.tvPercentChange.setTextColor(Color.parseColor("#FC0000"))
            holder.tvPercentChange.text = "$roundedPercentChange%"
        }

    }

    override fun getItemCount(): Int {
        return data.quoteResponse.result.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.stock_card)
        val tvName: TextView = card.findViewById(R.id.name)
        val tvPrice: TextView = card.findViewById(R.id.price)
        val tvPercentChange: TextView = card.findViewById(R.id.percentChange)
    }
}