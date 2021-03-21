package com.mytest.recrutimenttask_maciejstoinski.base

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.mytest.recrutimenttask_maciejstoinski.view.ui.AppTheme


@Composable
fun Home(content: @Composable () -> Unit) {
    AppTheme {
        Surface(color = MaterialTheme.colors.primary) {
            content()
        }
    }
}
