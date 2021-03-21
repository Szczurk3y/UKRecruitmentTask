package com.mytest.recrutimenttask_maciejstoinski.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityDetail(
    val name: String,
    val weather: Weather,
    val hourlyTemps: List<HourlyTemp>
): Parcelable {

    @Parcelize
    data class HourlyTemp(
        val temp: Int,
        val hour: Int
    ): Parcelable

    companion object {
        val providedCities = json
    }
}

enum class Weather {
    Rainy, Cloudy, Sunny
}