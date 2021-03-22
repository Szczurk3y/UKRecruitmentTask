package com.mytest.recrutimenttask_maciejstoinski.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytest.recrutimenttask_maciejstoinski.data.CitiesRepository
import com.mytest.recrutimenttask_maciejstoinski.model.CityDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    citiesRepository: CitiesRepository
): ViewModel() {

    val cities: List<CityDetail> = citiesRepository.cities
}