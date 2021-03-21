package com.mytest.recrutimenttask_maciejstoinski.view.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mytest.recrutimenttask_maciejstoinski.base.AppScaffold
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    AppScaffold {
        val transitionState = remember { MutableTransitionState(SplashState.Shown) }
        val transition = updateTransition(transitionState)
        val splashAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 100) }
        ) {
            if (it == SplashState.Shown) 1f
            else 0f
        }
        val contentAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 300) }
        ) {
            if (it == SplashState.Shown) 0f else 1f
        }
        val contentTopPadding by transition.animateDp(
            transitionSpec = { spring(stiffness = Spring.StiffnessLow) }
        ) {
            if (it == SplashState.Shown) 100.dp else 0.dp
        }

        Box {
            LandingScreen(
                modifier = Modifier.alpha(splashAlpha),
                onTimeout = { transitionState.targetState = SplashState.Completed }
            )
            MainContent(
                modifier = Modifier.alpha(contentAlpha),
                topPadding = contentTopPadding
            )
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    topPadding: Dp = 0.dp
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.padding(top = topPadding))
        AppHome()
    }
}

enum class SplashState { Shown, Completed }