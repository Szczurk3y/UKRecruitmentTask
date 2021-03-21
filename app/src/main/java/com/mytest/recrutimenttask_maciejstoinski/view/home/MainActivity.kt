package com.mytest.recrutimenttask_maciejstoinski.view.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.animation.core.animateFloat
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mytest.recrutimenttask_maciejstoinski.base.Home
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

        }
    }
}

@VisibleForTesting
@Composable
fun MainScreen() {
    Home {
        val transitionState = remember { MutableTransitionState(SplashState.Shown) }
        val transition = updateTransition(transitionState)
        val splashAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 100) }
        ) {
            if (it == SplashState.Shown) 1f
            else 0f
        }

        Box {
            LandingScreen(
                modifier = Modifier.alpha(splashAlpha),
                onTimeout = { transitionState.targetState = SplashState.Completed }
            )

        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    topPadding: Dp = 0.dp
    ) {

}

enum class SplashState { Shown, Completed }