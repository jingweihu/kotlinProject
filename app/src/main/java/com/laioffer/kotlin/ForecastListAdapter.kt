package com.laioffer.kotlin

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForecastListAdapter(val items: List<String>): RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.text = items[position]
    }

    class ViewHolder(val item: TextView) : RecyclerView.ViewHolder(item)
}