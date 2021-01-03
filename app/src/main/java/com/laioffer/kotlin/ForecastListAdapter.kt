package com.laioffer.kotlin

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laioffer.kotlin.domain.model.ForecastList

class ForecastListAdapter(var weekForecast: ForecastList): RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weekForecast[position]) {
            holder.item.text = "$date = $description - $high/$low"
        }
    }

    class ViewHolder(val item: TextView) : RecyclerView.ViewHolder(item)
}