package com.mytest.recrutimenttask_maciejstoinski.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytest.recrutimenttask_maciejstoinski.model.CityDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {

    private val _cities = MutableLiveData<List<CityDetail>>()
    val cities: LiveData<List<CityDetail>> get() = _cities

}