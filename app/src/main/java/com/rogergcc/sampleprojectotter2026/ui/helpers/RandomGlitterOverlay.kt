package com.rogergcc.sampleprojectotter2026.ui.helpers

/**
 * Created on agosto.
 * year 2026 .
 */


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun RandomGlitterOverlay(
    modifier: Modifier = Modifier,
    dotRadius: Dp = 1.5.dp,
    densityFactor: Int = 180, // Cantidad de puntos dispersos
    color: Color = Color.Black.copy(alpha = 0.25f),
    offsetXPx: Float = 0f,
    offsetYPx: Float = 0f,
    seed: Long = 42L // Mantiene la posición fija por ticket
) {
    // Generamos las posiciones "aleatorias" una sola vez mediante remember
    val points = remember(seed, densityFactor) {
        val rnd = Random(seed)
        List(densityFactor) {
            // Coordenadas fijas normalizadas (0.0f a 1.0f)
            Pair(rnd.nextFloat(), rnd.nextFloat())
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val dotRadiusPx = dotRadius.toPx()
        val width = size.width
        val height = size.height

        points.forEach { (normalizedX, normalizedY) ->
            // Mapeamos a píxeles + aplicamos el desplazamiento de la luz/giroscopio
            val baseX = normalizedX * width
            val baseY = normalizedY * height

            // Efecto cíclico para que los puntos reaparezcan por el otro lado al inclinarse
            val x = (baseX + offsetXPx).mod(width)
            val y = (baseY + offsetYPx).mod(height)

            drawCircle(
                color = color,
                radius = dotRadiusPx,
                center = Offset(x, y)
            )
        }
    }
}