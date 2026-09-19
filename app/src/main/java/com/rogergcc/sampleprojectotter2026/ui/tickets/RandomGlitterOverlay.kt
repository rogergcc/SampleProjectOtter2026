package com.rogergcc.sampleprojectotter2026.ui.tickets

/**
 * Created on agosto.
 * year 2026 .
 */


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
    seed: Long = 42L, // Mantiene la posición fija por ticket
) {
    // Generamos las posiciones "aleatorias" una sola vez mediante remember
    val points = remember(seed, densityFactor) {
        val rnd = Random(seed)
        List(densityFactor) {
            // Coordenadas fijas normalizadas (0.0f a 1.0f)
            Pair(rnd.nextFloat(), rnd.nextFloat())
        }
    }

    Canvas(modifier = modifier
        .background(
            Color(0xFF1A1A1A)
        )
        .fillMaxSize()) {
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

@Preview(showBackground = true)
@Composable
fun RandomGlitterOverlayPreview() {
    RandomGlitterOverlay(
        modifier = Modifier.fillMaxSize(),
        dotRadius = 2.dp,
        densityFactor = 180,
        color = Color(0xFFFF0053).copy(alpha = 0.28f),
        offsetXPx = 10f,
        offsetYPx = 5f,
        seed = 202L
    )
}

@Composable
private fun DefaultGlitterGroup(
    pitchProvider: () -> Float,
    rollProvider: () -> Float,
    modifier: Modifier = Modifier,
) {


    Box(
        modifier = modifier
    ) {
        RandomGlitterOverlay(
            modifier = Modifier.matchParentSize(),
            dotRadius = 0.9.dp,
            densityFactor = 300,
            color = Color(0xFFDE00FF).copy(alpha = 0.20f),
            offsetXPx = rollProvider() * 2.2f,
            offsetYPx = pitchProvider() * 2.2f,
            seed = 101L
        )

        RandomGlitterOverlay(
            modifier = Modifier.matchParentSize(),
            dotRadius = 1.7.dp,
            densityFactor = 180,
            color = Color(0xFFFF0053).copy(alpha = 0.28f),
            offsetXPx = -rollProvider() * 1.4f,
            offsetYPx = -pitchProvider() * 1.4f,
            seed = 202L
        )

        RandomGlitterOverlay(
            modifier = Modifier.matchParentSize(),
            dotRadius = 3.2.dp,
            densityFactor = 220,
            color = Color(0xFF14FF22).copy(alpha = 0.22f),
            offsetXPx = rollProvider() * 1.6f,
            offsetYPx = -pitchProvider() * 1.6f,
            seed = 303L
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultGlitterGroupPreview() {

    var isAutoAnimate by remember { mutableStateOf(true) }

// 3. Sensor ÚNICO compartido para la pasarela activa
    val sensorState by rememberTicketSensors()
    val activeRotation by rememberSimulatedOrRealSensors(
        isAutoAnimate = isAutoAnimate,
        realPitch = sensorState.pitch,
        realRoll = sensorState.roll
    )

    DefaultGlitterGroup(
        pitchProvider = { activeRotation.first },
        rollProvider = { activeRotation.second },
        modifier = Modifier.fillMaxSize()
    )
}