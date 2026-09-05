package com.rogergcc.sampleprojectotter2026.ui.helpers

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.TextUnit
import com.rogergcc.sampleprojectotter2026.ui.model.TicketHologramConfig
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

// ==========================================
// 2. GEOMETRÍA Y HELPERS (GenericShape)
// ==========================================

fun Path.addSideNotch(x: Float, centerY: Float, radius: Float, isRightSide: Boolean) {
    val startAngle = if (isRightSide) -90f else 90f
    val rectLeft = if (isRightSide) x - radius else -radius
    val rectRight = if (isRightSide) x + radius else radius

    lineTo(x, centerY - radius)
    arcTo(
        rect = Rect(
            left = rectLeft,
            top = centerY - radius,
            right = rectRight,
            bottom = centerY + radius
        ),
        startAngleDegrees = startAngle,
        sweepAngleDegrees = -180f,
        forceMoveTo = false
    )
}
fun Modifier.cropVerticalPadding(fontSize: TextUnit, cropPercentage: Float = 0.15f): Modifier = this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)

    // Calculamos el recorte basado en el porcentaje del tamaño de la fuente
    val fontSizeInPx = fontSize.toPx()
    val cropPx = (fontSizeInPx * cropPercentage).toInt()

    val newHeight = placeable.height - (cropPx * 2)

    layout(placeable.width, newHeight) {
        placeable.placeRelative(0, -cropPx)
    }
}

// 1. RAINBOW_GLASS: Espectro completo tipo prisma que rota con el giroscopio
fun DrawScope.drawRainbowGlassPattern(
    config: TicketHologramConfig,
    shiftX: Float,
    shiftY: Float
) {
    val rainbowColors = config.customFoilColors.ifEmpty {
        listOf(
            Color(0xFFFFEA00), // Amarillo
            Color(0xFF00FF66), // Verde
            Color(0xFF00E5FF), // Azul
            Color(0xFF9D00FF), // Morado
            Color(0xFFFF0055), // Magenta
            Color(0xFFFFEA00)  // Retorno
        )
    }

    val dynamicCenter = Offset(
        x = size.width * 0.5f + (shiftX * size.width * 0.45f),
        y = size.height * 0.5f + (shiftY * size.height * 0.45f)
    )

    drawRect(
        brush = Brush.radialGradient(
            colors = rainbowColors,
            center = dynamicCenter,
            radius = size.width * 0.9f
        ),
        blendMode = BlendMode.ColorDodge
    )
}

// 2. DIAMOND_CRYSTAL: Destello angular en abanico (SweepGradient)
fun DrawScope.drawDiamondCrystalPattern(
    config: TicketHologramConfig,
    shiftX: Float,
    shiftY: Float
) {
    val diamondColors = config.customFoilColors.ifEmpty {
        listOf(
            Color(0x8800E5FF), Color(0xAAFF007F), Color(0x88FFD700),
            Color(0xAA00FF66), Color(0x8800E5FF)
        )
    }

    val dynamicCenter = Offset(
        x = size.width * 0.5f + (shiftX * size.width * 0.35f),
        y = size.height * 0.5f + (shiftY * size.height * 0.35f)
    )

    drawRect(
        brush = Brush.sweepGradient(
            colors = diamondColors,
            center = dynamicCenter
        ),
        blendMode = BlendMode.Overlay
    )
}

// 3. STAR_FOIL: Franjas diagonales de luz especular intensa
fun DrawScope.drawStarFoilPattern(
    config: TicketHologramConfig,
    shiftX: Float,
    shiftY: Float
) {
    val starColors = config.customFoilColors.ifEmpty {
        listOf(
            Color.Transparent,
            Color.Black.copy(alpha = config.overlayIntensity * 0.3f),
            Color.Black.copy(alpha = config.overlayIntensity),
            Color.Black.copy(alpha = config.overlayIntensity * 0.3f),
            Color.Transparent
        )
    }

    drawRect(
        brush = Brush.linearGradient(
            colors = starColors,
            start = Offset((shiftX - 0.2f) * size.width, (shiftY - 0.2f) * size.height),
            end = Offset((shiftX + 0.8f) * size.width, (shiftY + 0.8f) * size.height)
        ),
        blendMode = BlendMode.Plus
    )
}

// 4. LIQUID_OIL_SLATE: Reflejo fluido ahumado/aceitoso en expansión
fun DrawScope.drawLiquidOilSlatePattern(
    config: TicketHologramConfig,
    shiftX: Float,
    shiftY: Float
) {
    val oilColors = config.customFoilColors.ifEmpty {
        listOf(
            Color(0x9900FFFF), Color(0x99FF00FF), Color(0x99FFFF00), Color.Transparent
        )
    }

    drawRect(
        brush = Brush.radialGradient(
            colors = oilColors,
            center = Offset(
                size.width * 0.5f + (shiftX * size.width * 0.4f),
                size.height * 0.5f + (shiftY * size.height * 0.4f)
            ),
            radius = size.width * 0.85f
        ),
        blendMode = BlendMode.Screen
    )
}

/**
 * Crea un Brush lineal con un ángulo exacto en grados.
 */
fun angledGradient(
    degrees: Float,
    vararg colorStops: Pair<Float, Color>
): ShaderBrush {
    val angleRad = Math.toRadians(degrees.toDouble())
    val direction = Offset(sin(angleRad).toFloat(), -cos(angleRad).toFloat())

    return object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val length = size.width * abs(direction.x) + size.height * abs(direction.y)
            val start = size.center - direction * (length / 2f)
            val end = size.center + direction * (length / 2f)

            return LinearGradientShader(
                colors = colorStops.map { it.second },
                colorStops = colorStops.map { it.first },
                from = start,
                to = end
            )
        }
    }

}

