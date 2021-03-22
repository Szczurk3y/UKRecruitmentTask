package com.mytest.recrutimenttask_maciejstoinski.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityDetail(
    @Json(name = "city") val name: String,
    val weather: Weather,
    @Json(name = "hourly_temp") val hourlyTemps: List<HourlyTemp>
): Parcelable {

    @Parcelize
    data class HourlyTemp(
        val temp: Float,
        val hour: Int
    ): Parcelable
}

enum class Weather {
    Rainy, Cloudy, Sunny
}