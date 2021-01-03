package com.laioffer.kotlin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laioffer.kotlin.data.ForecastRequest
import com.laioffer.kotlin.domain.commands.Command
import com.laioffer.kotlin.domain.mappers.ForecastDataMapper
import com.laioffer.kotlin.domain.model.ForecastList
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val scope = CoroutineScope(Job() + Dispatchers.Main)
    val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
            "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                RequestForecastCommand("94043").execute()
            }
            forecastList.adapter = ForecastListAdapter(result)
        }
    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }


    class RequestForecastCommand(val zipCode: String): Command<ForecastList> {
        override suspend fun execute(): ForecastList {
            val forecastRequest = ForecastRequest(zipCode)
            return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
        }
    }
}