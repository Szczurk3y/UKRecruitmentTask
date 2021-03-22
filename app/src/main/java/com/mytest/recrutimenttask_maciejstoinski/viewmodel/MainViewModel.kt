package com.mytest.recrutimenttask_maciejstoinski.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytest.recrutimenttask_maciejstoinski.data.CitiesRepository
import com.mytest.recrutimenttask_maciejstoinski.model.CityDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    citiesRepository: CitiesRepository
): ViewModel() {

    private val cities = citiesRepository.cities


    private val _citiesLiveData = MutableLiveData<List<CityDetail>>()
    val citiesLiveData: LiveData<List<CityDetail>> get() = _citiesLiveData

    init {
        _citiesLiveData.value = cities
    }

    fun findLowestTemperature() {
        val tempCities = cities.toMutableList()
        viewModelScope.launch {
            tempCities.sortBy {
                it.hourlyTemps.map { it.temp }.minOrNull()
            }
            _citiesLiveData.value = listOf(tempCities.first())
        }
    }
}