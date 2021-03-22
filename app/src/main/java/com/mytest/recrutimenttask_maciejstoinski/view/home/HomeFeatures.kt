package com.mytest.recrutimenttask_maciejstoinski.view.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mytest.recrutimenttask_maciejstoinski.R

@Composable
fun CitiesSearchContent(searchContentUpdates: SearchContentUpdates) {
    CitySearch {
        FindSmallestTemperature(
            modifier = Modifier.clickable(onClick = searchContentUpdates.onFindLowestTemperatureClicked),
            vectorImageId = R.drawable.ic_search
        ) {
            Text(text = "Find lowest temperature", style = MaterialTheme.typography.body1.copy(color = Color.White))
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun FindSmallestTemperature(
    modifier: Modifier = Modifier,
    @DrawableRes vectorImageId: Int? = null,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.primaryVariant
    ) {
        Row(modifier = Modifier.padding(all = 12.dp)) {
            if (vectorImageId != null) {
                Icon(
                    modifier = Modifier.size(24.dp, 24.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    tint = Color(0x80FFFFFF),
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
            }

            Row(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)) {
                content()
            }
        }
    }
}

@Composable
private fun CitySearch(content: @Composable () -> Unit) {
    Column(Modifier.padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 12.dp)) {
        content()
    }
}