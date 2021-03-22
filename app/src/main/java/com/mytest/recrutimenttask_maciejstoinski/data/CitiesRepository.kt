package com.mytest.recrutimenttask_maciejstoinski.data

import com.mytest.recrutimenttask_maciejstoinski.model.CityDetail
import com.mytest.recrutimenttask_maciejstoinski.model.Weather
import com.mytest.recrutimenttask_maciejstoinski.model.json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CitiesRepository @Inject constructor(
    moshi: Moshi
) {
    val type = Types.newParameterizedType(List::class.java, CityDetail::class.java)

    val jsonAdapter: JsonAdapter<List<CityDetail>> = moshi.adapter(type)

    val cities: List<CityDetail> = jsonAdapter.fromJson(json) ?: emptyList()

}