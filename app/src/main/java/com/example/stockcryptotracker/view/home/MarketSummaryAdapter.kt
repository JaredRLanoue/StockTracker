package com.example.stockcryptotracker.view.home

import android.content.Context
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
import com.example.stockcryptotracker.view.details.DetailsActivity


class MarketSummaryAdapter(private val data: FinanceData) :
    RecyclerView.Adapter<MarketSummaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.stock_card_cube, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val stock = data.quoteResponse.result[position]

        holder.tvName.text = stock.shortName

        if (stock.regularMarketPrice > 0.01) { // altcoins on the weekends are lower than 0.01, need to show their prices.
            holder.tvPrice.text = stock.regularMarketPrice.toString()
        } else {
            holder.tvPrice.text = String.format("%.2f", stock.regularMarketPrice)
        }

        val roundedPercentChange =
            String.format("%.2f", stock.regularMarketChangePercent)

        if (stock.regularMarketChangePercent >= 0) {
            holder.tvPercentChange.setTextColor(Color.parseColor("#00D964"))
            holder.tvPercentChange.text = "+$roundedPercentChange%"
        } else {
            holder.tvPercentChange.setTextColor(Color.parseColor("#FC0000"))
            holder.tvPercentChange.text = "$roundedPercentChange%"
        }

        holder.tvCard.setOnClickListener {
            val context = holder.tvCard.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("id", stock.symbol.toString());
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.quoteResponse.result.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCard: CardView = view.findViewById(R.id.stock_card_cube)
        val tvName: TextView = tvCard.findViewById(R.id.name)
        val tvPrice: TextView = tvCard.findViewById(R.id.price)
        val tvPercentChange: TextView = tvCard.findViewById(R.id.percentChange)
    }
}