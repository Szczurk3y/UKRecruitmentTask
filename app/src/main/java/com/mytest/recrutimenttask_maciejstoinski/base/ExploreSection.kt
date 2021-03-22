package com.mytest.recrutimenttask_maciejstoinski.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mytest.recrutimenttask_maciejstoinski.model.CityDetail
import com.mytest.recrutimenttask_maciejstoinski.view.home.OnCityDetailItemClicked
import com.mytest.recrutimenttask_maciejstoinski.view.ui.BottomSheetShape
import androidx.compose.material.Text
import com.mytest.recrutimenttask_maciejstoinski.view.ui.app_caption
import com.mytest.recrutimenttask_maciejstoinski.view.ui.app_divider_color

@Composable
fun ExploreSection(
    modifier: Modifier = Modifier,
    title: String,
    citiesList: List<CityDetail>,
    onItemClicked: OnCityDetailItemClicked
) {
    Surface(modifier = modifier.fillMaxSize(), color = Color.White, shape = BottomSheetShape) {
        Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.caption.copy(color = app_caption)
            )
            Spacer(Modifier.height(8.dp))
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(citiesList) { city_item ->
                    Column(Modifier.fillParentMaxWidth()) {
                        CityItem(
                            modifier = Modifier.fillParentMaxWidth(),
                            item = city_item,
                            onItemClicked = onItemClicked
                        )
                        Divider(color = app_divider_color)
                    }
                }
            }
        }
    }
}

@Composable
private fun CityItem(
    modifier: Modifier = Modifier,
    item: CityDetail,
    onItemClicked: OnCityDetailItemClicked
) {
    Row(
        modifier = modifier
            .clickable { onItemClicked(item) }
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        Column {
            Text(
                text = item.name,
                style = MaterialTheme.typography.h6
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "${item.weather.name}, highest temp: ${item.hourlyTemps.map { it.temp }.maxOrNull()}",
                style = MaterialTheme.typography.caption.copy(color = app_caption)
            )
        }
    }
}