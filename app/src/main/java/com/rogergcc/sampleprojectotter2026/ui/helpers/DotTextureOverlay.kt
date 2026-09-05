package com.rogergcc.sampleprojectotter2026.ui.helpers

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DotTextureOverlay(
    modifier: Modifier = Modifier,
    dotRadius: Dp = 1.5.dp,
    spacing: Dp = 8.dp,
    color: Color = Color.White.copy(alpha = 0.15f),
    offsetXPx: Float = 0f,
    offsetYPx: Float = 0f
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val dotRadiusPx = dotRadius.toPx()
        val spacingPx = spacing.toPx()

        // Calculamos columnas y filas extendidas para evitar bordes vacíos al desplazar
        val cols = ((size.width + spacingPx * 2) / spacingPx).toInt()
        val rows = ((size.height + spacingPx * 2) / spacingPx).toInt()

        for (i in -1..cols) {
            for (j in -1..rows) {
                val alternateOffset = if (j % 2 == 0) 0f else spacingPx / 2f
                val x = (i * spacingPx) + alternateOffset + (offsetXPx % spacingPx)
                val y = (j * spacingPx) + (offsetYPx % spacingPx)

                drawCircle(
                    color = color,
                    radius = dotRadiusPx,
                    center = Offset(x, y)
                )
            }
        }
    }
}