package com.onurcan.core.ui.screen

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        LiquidLoadingAnimation()
    }
}

@Composable
private fun LiquidLoadingAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "liquidLoading")
    LiquidCircle(infiniteTransition, delayMillis = 0, durationMillis = 2500)
    LiquidCircle(infiniteTransition, delayMillis = 600, durationMillis = 2200)
    LiquidCircle(infiniteTransition, delayMillis = 1200, durationMillis = 2800)
    LiquidCircle(infiniteTransition, delayMillis = 1800, durationMillis = 2400)
}

@Composable
private fun LiquidCircle(
    infiniteTransition: InfiniteTransition,
    delayMillis: Int,
    durationMillis: Int
) {
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, delayMillis = delayMillis, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "scale"
    )

    val animatedAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis, delayMillis = delayMillis, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "alpha"
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = durationMillis * 2, delayMillis = delayMillis, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    Box(
        modifier = Modifier
            .size(80.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = animatedAlpha
                rotationZ = rotation
            }
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                shape = CircleShape
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen()
}
