package com.laioffer.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laioffer.kotlin.domain.model.Forecast
import com.laioffer.kotlin.domain.model.ForecastList
import com.squareup.picasso.Picasso

class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ForecastListAdapter.ViewHolder, position: Int) {
       holder.bindForecast(weekForecast[position])
    }

    class ViewHolder(val view: View, val itemClick: (Forecast) -> Unit) :
        RecyclerView.ViewHolder(view) {

        private val iconView = view.findViewById<ImageView>(R.id.icon)
        private val dateView = view.findViewById<TextView>(R.id.date)
        private val descriptionView = view.findViewById<TextView>(R.id.description)
        private val maxTemperatureView = view.findViewById<TextView>(R.id.maxTemperature)
        private val minTemperatureView =
            view.findViewById<TextView>(R.id.minTemperature)

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.get().load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.setOnClickListener{ itemClick.invoke(this)}

            }
        }
    }
}