package com.example.stockcryptotracker.view.search

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
import com.example.stockcryptotracker.dto.SearchData
import com.example.stockcryptotracker.dto.TrendingData
import com.example.stockcryptotracker.view.details.DetailsActivity


class SearchAdapter(private val data: SearchData) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.stock_card_search, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = data.ResultSet.Result[position].name
        holder.tvSymbol.text = data.ResultSet.Result[position].symbol
        holder.card.setOnClickListener {
            val context = holder.card.context
            val intent = Intent(context, DetailsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.ResultSet.Result.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: CardView = view.findViewById(R.id.stock_card_search)
        val tvName: TextView = card.findViewById(R.id.name)
        val tvSymbol: TextView = card.findViewById(R.id.symbol)
    }
}