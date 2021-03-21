package com.mytest.recrutimenttask_maciejstoinski.view.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val app_red = Color(0xFFE30425)
private val app_white = Color.White
private val app_purple_700 = Color(0xFF720D5D)
private val app_purple_800 = Color(0xFF5D1049)
private val app_purple_900 = Color(0xFF4E0D3A)

val appColors = lightColors(
    primary = app_purple_800,
    secondary = app_red,
    surface = app_purple_900,
    onSurface = app_white,
    primaryVariant = app_purple_700
)

val BottomSheetShape = RoundedCornerShape(
    topStart = 20.dp,
    topEnd = 20.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = appColors, typography = appTypography) {
        content()
    }
}