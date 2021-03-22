package com.mytest.recrutimenttask_maciejstoinski.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
    val viewModel: MainViewModel = viewModel()

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
        frontLayerScrimColor = Color.Transparent,
        appBar = { HomeBar() },
        backLayerContent = {
            SearchContent(
                viewModel = viewModel,
                onCityDetailItemClicked
            )
        },
        frontLayerContent = {
            ExploreSection(
                title = "Available cities:",
                citiesList = viewModel.cities,
                onItemClicked = onCityDetailItemClicked
            )
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
    viewModel: MainViewModel,
    onCityDetailItemClicked: OnCityDetailItemClicked
) {

}