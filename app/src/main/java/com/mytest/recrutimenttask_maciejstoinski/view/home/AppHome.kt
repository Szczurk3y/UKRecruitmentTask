package com.mytest.recrutimenttask_maciejstoinski.view.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mytest.recrutimenttask_maciejstoinski.base.ExploreSection
import com.mytest.recrutimenttask_maciejstoinski.model.CityDetail
import com.mytest.recrutimenttask_maciejstoinski.viewmodel.MainViewModel

typealias OnCityDetailItemClicked = (CityDetail) -> Unit

@Composable
fun AppHome(
    onCityDetailItemClicked: OnCityDetailItemClicked,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    Column {
        val scope = rememberCoroutineScope()
        AppHomeContent(
            onCityDetailItemClicked = onCityDetailItemClicked,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppHomeContent(
    onCityDetailItemClicked: OnCityDetailItemClicked,
    modifier: Modifier = Modifier
) {
    val viewmodel: MainViewModel = viewModel()
    val citiesToDisplay by viewmodel.citiesLiveData.observeAsState()

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
        frontLayerScrimColor = Color.Transparent,
        appBar = { HomeBar() },
        backLayerContent = {
            SearchContent(
                viewmodel = viewmodel
            )
        },
        frontLayerContent = {
            citiesToDisplay?.let { cities ->
                ExploreSection(
                    title = "Available cities:",
                    citiesList = cities,
                    onItemClicked = onCityDetailItemClicked
                )
            }
        }
    )
}

@Composable
private fun HomeBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(modifier) {
        Text("Recruitment test")
    }
}

@Composable
private fun SearchContent(
    viewmodel: MainViewModel
) {
    CitiesSearchContent(SearchContentUpdates(
        onFindLowestTemperatureClicked = {
            Log.i("Lowest temp clicked", "Find lowest temp clicked")
            viewmodel.findLowestTemperature()
        }
    ))
}

data class SearchContentUpdates(
    val onFindLowestTemperatureClicked: () -> Unit
)