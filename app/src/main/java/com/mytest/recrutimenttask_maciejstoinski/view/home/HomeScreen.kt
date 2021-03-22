package com.mytest.recrutimenttask_maciejstoinski.view.home

import android.util.Log
import android.view.MenuItem
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FiberManualRecord
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.mytest.recrutimenttask_maciejstoinski.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mytest.recrutimenttask_maciejstoinski.base.ExploreSection
import com.mytest.recrutimenttask_maciejstoinski.model.CityDetail
import com.mytest.recrutimenttask_maciejstoinski.viewmodel.MainViewModel
import kotlinx.coroutines.launch

typealias OnCityDetailItemClicked = (CityDetail) -> Unit

@Composable
fun HomeScreen(
    onCityDetailItemClicked: OnCityDetailItemClicked,
    modifier: Modifier = Modifier
) {
    val showMenu = remember { mutableStateOf(false)}
    Column {
        val scope = rememberCoroutineScope()
        AppHomeContent(
            onCityDetailItemClicked = onCityDetailItemClicked,
            modifier = modifier,
            showMenu = showMenu,
            openFilter = {
                showMenu.value = !showMenu.value
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppHomeContent(
    onCityDetailItemClicked: OnCityDetailItemClicked,
    modifier: Modifier = Modifier,
    openFilter: () -> Unit,
    showMenu: MutableState<Boolean>
) {
    val viewmodel: MainViewModel = viewModel()
    val citiesToDisplay by viewmodel.citiesLiveData.observeAsState()

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
        frontLayerScrimColor = Color.Transparent,
        appBar = {
            HomeBar(modifier = modifier, onMenuClicked = openFilter) {
                Text(
                    modifier = modifier,
                    text = "Recruitment Test"
                )
            }
        },
        backLayerContent = {
            SearchContent(
                viewmodel = viewmodel,
                showMenu = showMenu
            ) { filter ->
                when(filter) {
                    Filter.Reset -> {
                        Log.i("Reset clicked", "bump!")
                        viewmodel.resetCities()
                        showMenu.value = false
                    }
                }
            }
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
    modifier: Modifier = Modifier,
    onMenuClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    TopAppBar(
        title = {
            content()
        },
        elevation = 8.dp,
        navigationIcon = {
            Image(
                 modifier = Modifier
                     .padding(all = 8.dp)
                     .clickable(onClick = onMenuClicked),
                painter = painterResource(id = R.drawable.ic_baseline_filter_list_24),
                contentDescription = null
            )
        }
    )
}

@Composable
fun FilterMenu(
    modifier: Modifier,
    showMenu: Boolean,
    onFilterChange: (Filter) -> Unit
) {
    Card(
        modifier = modifier
            .animateContentSize(),
        elevation = 8.dp
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            if (showMenu) {
                MenuItem(name = "Reset") {
                    onFilterChange.invoke(Filter.Reset)
                }
            }
        }
    }
}

@Composable
private fun MenuItem(
    name: String,
    onFilterChange: () -> Unit)
{
    Row(
        modifier = Modifier
            .clickable(onClick = onFilterChange),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, modifier = Modifier.padding(16.dp))
    }
}

@Composable
private fun SearchContent(
    viewmodel: MainViewModel,
    showMenu: MutableState<Boolean>,
    onFilterChange: (Filter) -> Unit
) {
    Box() {
        CitiesSearchContent(SearchContentUpdates(
            onFindLowestTemperatureClicked = {
                Log.i("Lowest temp clicked", "Find lowest temp clicked")
                viewmodel.findLowestTemperature()
            }
        ))

        FilterMenu(
            modifier = Modifier.align(Alignment.TopStart),
            showMenu = showMenu.value,
            onFilterChange = onFilterChange
        )
    }
}

data class SearchContentUpdates(
    val onFindLowestTemperatureClicked: () -> Unit
)

enum class Filter { Reset }