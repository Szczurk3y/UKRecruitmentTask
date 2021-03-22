package com.mytest.recrutimenttask_maciejstoinski.data.adapters

import com.mytest.recrutimenttask_maciejstoinski.model.Weather
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson
import java.util.*

class WeatherAdapter {

    @ToJson
    fun toJson(weather: Weather): String {
        return weather.name
    }

    @FromJson
    fun fromJson(weather: String): Weather =
        when (weather.capitalize(Locale.getDefault())) {
            Weather.Cloudy.name -> {
                Weather.Cloudy
            }
            Weather.Rainy.name -> {
                Weather.Rainy
            }
            Weather.Sunny.name -> {
                Weather.Sunny
            }
            else -> throw JsonDataException("Unknown weather type $weather")

        }

}