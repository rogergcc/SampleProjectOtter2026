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
    densityFactor: Int = 180,
    color: Color = Color.Black.copy(alpha = 0.25f),
    offsetXPx: Float = 0f,
    offsetYPx: Float = 0f,
    seed: Long = 42L,
) {
    val points = remember(seed, densityFactor) {
        val rnd = Random(seed)
        List(densityFactor) {
            Pair(rnd.nextFloat(), rnd.nextFloat())
        }
    }

    // Eliminamos .background(Color(0xFF1A1A1A)) de aquí
    Canvas(modifier = modifier.fillMaxSize()) {
        val dotRadiusPx = dotRadius.toPx()
        val width = size.width
        val height = size.height

        points.forEach { (normalizedX, normalizedY) ->
            val baseX = normalizedX * width
            val baseY = normalizedY * height

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
fun DefaultGlitterGroup(
    pitchProvider: () -> Float,
    rollProvider: () -> Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
//            .background(Color(0xFF9317A9)) // Fondo oscuro único para el grupo
    ) {
        // Capa 1: Puntos púrpuras
        RandomGlitterOverlay(
            modifier = Modifier.matchParentSize(),
            dotRadius = 0.9.dp,
            densityFactor = 300,
            color = Color(0xFFDE2FFC).copy(alpha = 0.50f), // Incrementa un poco el alpha si deseas más contraste
            offsetXPx = rollProvider() * 2.2f,
            offsetYPx = pitchProvider() * 2.2f,
            seed = 101L
        )

        // Capa 2: Puntos rosados
        RandomGlitterOverlay(
            modifier = Modifier.matchParentSize(),
            dotRadius = 1.7.dp,
            densityFactor = 180,
            color = Color(0xFF0088FF).copy(alpha = 0.50f),
            offsetXPx = -rollProvider() * 1.4f,
            offsetYPx = -pitchProvider() * 1.4f,
            seed = 202L
        )

        // Capa 3: Puntos verdes
        RandomGlitterOverlay(
            modifier = Modifier.matchParentSize(),
            dotRadius = 1.2.dp,
            densityFactor = 220,
            color = Color(0xFFFAC113).copy(alpha = 0.40f),
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