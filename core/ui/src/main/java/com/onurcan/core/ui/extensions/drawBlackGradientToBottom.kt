package com.onurcan.core.ui.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.drawBlackGradientToBottom(startYPercentage: Float = 0.5f): Modifier = this.then(
    Modifier.drawWithContent {
        drawContent()
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, Color.Black),
                startY = size.height * startYPercentage
            )
        )
    }
)