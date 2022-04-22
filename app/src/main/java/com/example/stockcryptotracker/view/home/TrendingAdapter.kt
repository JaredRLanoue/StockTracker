package com.example.stockcryptotracker.view.home

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockcryptotracker.R
import com.example.stockcryptotracker.dto.FinanceData
import com.example.stockcryptotracker.dto.TrendingData
import com.example.stockcryptotracker.view.details.DetailsActivity


class TrendingAdapter(private val data: FinanceData) :
    RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.stock_card_rectangle, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = data.quoteResponse.result[position].shortName
        holder.tvPrice.text = String.format("%.2f", data.quoteResponse.result[position].regularMarketPrice)

        val roundedPercentChange =
            String.format("%.2f", data.quoteResponse.result[position].regularMarketChangePercent)

        if (data.quoteResponse.result[position].regularMarketChangePercent >= 0) {
            holder.tvPercentChange.setTextColor(Color.parseColor("#00D964"))
            holder.tvPercentChange.text = "+$roundedPercentChange%"
        } else {
            holder.tvPercentChange.setTextColor(Color.parseColor("#FC0000"))
            holder.tvPercentChange.text = "$roundedPercentChange%"
        }

        holder.card.setOnClickListener {
            val context = holder.card.context
            val intent = Intent(context, DetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.quoteResponse.result.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.stock_card_rectangle)
        val tvName: TextView = card.findViewById(R.id.name)
        val tvPrice: TextView = card.findViewById(R.id.price)
        val tvPercentChange: TextView = card.findViewById(R.id.percentChange)
    }
}