package com.rogergcc.sampleprojectotter2026.ui.composables

import android.R.attr.rotationX
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rogergcc.sampleprojectotter2026.ui.helpers.RandomGlitterOverlay
import com.rogergcc.sampleprojectotter2026.ui.helpers.addSideNotch
import com.rogergcc.sampleprojectotter2026.ui.helpers.cropVerticalPadding
import com.rogergcc.sampleprojectotter2026.ui.helpers.drawDiamondCrystalPattern
import com.rogergcc.sampleprojectotter2026.ui.helpers.drawLiquidOilSlatePattern
import com.rogergcc.sampleprojectotter2026.ui.helpers.drawRainbowGlassPattern
import com.rogergcc.sampleprojectotter2026.ui.helpers.drawStarFoilPattern
import com.rogergcc.sampleprojectotter2026.ui.model.FoilPatternType
import com.rogergcc.sampleprojectotter2026.ui.model.TicketCutStyle
import com.rogergcc.sampleprojectotter2026.ui.model.TicketHologramConfig
import com.rogergcc.sampleprojectotter2026.ui.model.TicketModel
import com.rogergcc.sampleprojectotter2026.ui.model.TopCutType
import com.rogergcc.sampleprojectotter2026.ui.theme.AppDimens
import java.nio.file.Files.size


/**
 * Created on septiembre.
 * year 2026 .
 */

@Composable
fun CommemorativeTicketCard(
    ticket: TicketModel,
    pitchProvider: () -> Float, // Lambda para diferir lectura
    rollProvider: () -> Float,  // Lambda para diferir lectura
    modifier: Modifier = Modifier
) {
    val ticketShape = remember(ticket.cutStyle) { createTicketShape(ticket.cutStyle) }

    Card(
        modifier = modifier
            .widthIn(max = AppDimens.MaxTicketWidth)
            .padding(horizontal = AppDimens.TicketHorizontalPadding)
            .fillMaxWidth()
            .aspectRatio(320f / 560f)
            .graphicsLayer {
                // FASE DE DIBUJO: Zero Re-compositions durante el movimiento
                val pitch = pitchProvider()
                val roll = rollProvider()

                rotationX = (-pitch * 0.35f).coerceIn(-18f, 18f)
                rotationY = (roll * 0.35f).coerceIn(-18f, 18f)
                cameraDistance = 14f * density
                shadowElevation = 10.dp.toPx()
            },
        shape = ticketShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ticket.backgroundBrush)
        ) {
            // 2. Capa de Textura o Efecto Glitter
            TicketTextureOverlay(
                config = ticket.hologramConfig,
                pitchProvider = pitchProvider,
                rollProvider = rollProvider
            )
// En la capa del fondo de Compose

            // 1. Capa de Contenido Tipográfico e Ilustraciones
            TicketContentLayout(ticket = ticket)



            // 3. Capa de Overlay Holográfico (Prisma)
            if (ticket.hologramConfig.patternType != FoilPatternType.NONE) {
                HolographicOverlay(
                    config = ticket.hologramConfig,
                    pitchProvider = pitchProvider,
                    rollProvider = rollProvider,
                    modifier = Modifier.matchParentSize()
                )
            }
        }
    }
}

/**
 * Crea la geometría custom para el troquelado del ticket.
 * Optimizada para no generar sobrecarga en el Garbage Collector.
 */
fun createTicketShape(cutStyle: TicketCutStyle) = GenericShape { size, _ ->
    val centerY = size.height * cutStyle.sideNotchYRatio
    val count = cutStyle.topTeethCount
    val spacing = cutStyle.teethSpacing
    val depth = cutStyle.topCutDepth
    val cornerR = cutStyle.cornerCutRadius

    reset()

    // 1. TROQUELADO SUPERIOR
    when (cutStyle.topCutType) {
        TopCutType.CONCAVE_TEETH -> {
            // Esquina superior izquierda cóncava
            moveTo(0f, cornerR)
            arcTo(
                rect = Rect(
                    left = -cornerR,
                    top = -cornerR,
                    right = cornerR,
                    bottom = cornerR
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = -90f,
                forceMoveTo = false
            )

            val availableWidth = size.width - (cornerR * 2)
            val totalSpacing = spacing * (count + 1)
            val toothDiameter = (availableWidth - totalSpacing) / count

            var currentX = cornerR
            for (i in 0 until count) {
                val startFlatX = currentX
                val endFlatX = startFlatX + spacing
                val toothEndX = endFlatX + toothDiameter

                lineTo(endFlatX, 0f)
                arcTo(
                    rect = Rect(
                        left = endFlatX,
                        top = -depth,
                        right = toothEndX,
                        bottom = depth
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = -180f,
                    forceMoveTo = false
                )
                currentX = toothEndX
            }

            lineTo(size.width - cornerR, 0f)

            // Esquina superior derecha cóncava
            arcTo(
                rect = Rect(
                    left = size.width - cornerR,
                    top = -cornerR,
                    right = size.width + cornerR,
                    bottom = cornerR
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = -90f,
                forceMoveTo = false
            )
        }

        TopCutType.CONVEX_TEETH, TopCutType.SAW_TEETH -> {
            val totalSpacing = spacing * (count + 1)
            val toothDiameter = (size.width - totalSpacing) / count

            moveTo(0f, depth)
            for (i in 0 until count) {
                val endFlatX = (i * (toothDiameter + spacing)) + spacing
                val toothEndX = endFlatX + toothDiameter

                lineTo(endFlatX, depth)
                if (cutStyle.topCutType == TopCutType.CONVEX_TEETH) {
                    arcTo(
                        rect = Rect(
                            left = endFlatX,
                            top = 0f,
                            right = toothEndX,
                            bottom = depth * 2f
                        ),
                        startAngleDegrees = 180f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                } else {
                    lineTo(endFlatX + (toothDiameter / 2f), 0f)
                    lineTo(toothEndX, depth)
                }
            }
            lineTo(size.width, depth)
        }

        TopCutType.NONE -> {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
        }
    }

    // 2. MUESCA LATERAL DERECHA
    if (cutStyle.showSideNotches) {
        addSideNotch(
            x = size.width,
            centerY = centerY,
            radius = cutStyle.sideNotchRadius,
            isRightSide = true
        )
    }

    // 3. ESQUINAS INFERIORES REDONDEADAS
    val bCorner = cutStyle.bottomCornerRadius
    lineTo(size.width, size.height - bCorner)
    arcTo(
        rect = Rect(
            left = size.width - (bCorner * 2),
            top = size.height - (bCorner * 2),
            right = size.width,
            bottom = size.height
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
    )

    lineTo(bCorner, size.height)
    arcTo(
        rect = Rect(
            left = 0f,
            top = size.height - (bCorner * 2),
            right = bCorner * 2,
            bottom = size.height
        ),
        startAngleDegrees = 90f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
    )

    // 4. MUESCA LATERAL IZQUIERDA
    if (cutStyle.showSideNotches) {
        addSideNotch(
            x = 0f,
            centerY = centerY,
            radius = cutStyle.sideNotchRadius,
            isRightSide = false
        )
    }

    close()
}

@Composable
private fun BoxScope.TicketTextureOverlay(
    config: TicketHologramConfig,
    pitchProvider: () -> Float,
    rollProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    config.textureRes?.let { customPattern ->
        val tintColor = config.customFoilColors.firstOrNull() ?: Color(0xFFFFEA00)

        Image(
            painter = painterResource(id = customPattern),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(
                color = tintColor.copy(alpha = config.baseAlpha),
                blendMode = BlendMode.SrcIn
            ),
            modifier = modifier
                .matchParentSize() // Ahora sí funciona correctamente
                .graphicsLayer {
                    translationX = -rollProvider() * 1.8f
                    translationY = -pitchProvider() * 1.8f
                    scaleX = 1.1f
                    scaleY = 1.1f
                }
        )
    } ?: run {
        DefaultGlitterGroup(
            pitchProvider = pitchProvider,
            rollProvider = rollProvider,
            modifier = modifier.matchParentSize()
        )
    }
}

@Composable
private fun TicketContentLayout(
    ticket: TicketModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Contenedor interno con Padding para el Texto y Logo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    top = AppDimens.TicketTopPadding,
                    start = AppDimens.PaddingMedium,
                    end = AppDimens.PaddingMedium
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. SECCIÓN CABECERA
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ticket.artistLogoRes?.let { logoDrawable ->
                    Image(
                        painter = painterResource(id = logoDrawable),
                        contentDescription = "Logo del Artista",
                        modifier = Modifier
                            .fillMaxWidth(0.98f)
                            .wrapContentHeight(),
                        contentScale = ContentScale.FillWidth
                    )
                }

                ticket.subtitleText?.let { subtitle ->
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = subtitle,
                        style = ticket.styleConfig.tourTitleStyle,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // 2. SECCIÓN CENTRADA (Fecha, Recinto, Ciudad)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        modifier = Modifier.cropVerticalPadding(
                            fontSize = ticket.styleConfig.venueStyle.fontSize,
                            cropPercentage = 0.15f
                        ),
                        text = ticket.dateText,
                        style = ticket.styleConfig.dateStyle,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.cropVerticalPadding(
                            fontSize = ticket.styleConfig.venueStyle.fontSize,
                            cropPercentage = 0.15f
                        ),
                        text = ticket.venueText,
                        style = ticket.styleConfig.venueStyle,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = ticket.cityText,
                        style = ticket.styleConfig.cityStyle,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // 3. SECCIÓN INFERIOR: Sin paddings para lograr el efecto de borde a borde (Bleed)
        ticket.illustrationRes?.let { illustrationDrawable ->
            Image(
                painter = painterResource(id = illustrationDrawable),
                contentDescription = "Ilustración Ticket",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppDimens.FooterIllustrationHeight),
                contentScale = ContentScale.Crop, // O ContentScale.FillWidth segun el aspect ratio de tu PNG
                alignment = Alignment.BottomCenter
            )
        }
    }
}

@Composable
private fun DefaultGlitterGroup(
    pitchProvider: () -> Float,
    rollProvider: () -> Float,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
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
            dotRadius = 1.2.dp,
            densityFactor = 220,
            color = Color(0xFFFCFCFC).copy(alpha = 0.22f),
            offsetXPx = rollProvider() * 1.6f,
            offsetYPx = -pitchProvider() * 1.6f,
            seed = 303L
        )
    }
}


@Composable
fun HolographicOverlay(
    config: TicketHologramConfig,
    pitchProvider: () -> Float,
    rollProvider: () -> Float,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        // Leemos las lambdas dentro del bloque de dibujo (Draw Phase)
        val pitch = pitchProvider()
        val roll = rollProvider()

        val shiftX = (roll / config.gyroSensitivity).coerceIn(-1f, 1f)
        val shiftY = (pitch / config.gyroSensitivity).coerceIn(-1f, 1f)

        val width = size.width
        val height = size.height

        // Capa base de destello blanco
        drawRect(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.Black.copy(alpha = config.baseAlpha * 0.2f),
                    Color.Black.copy(alpha = config.baseAlpha),
                    Color.Black.copy(alpha = config.baseAlpha * 0.1f)
                ),
                start = Offset((shiftX + 0.5f) * width * 0.2f, (shiftY + 0.5f) * height * 0.2f),
                end = Offset((shiftX + 1.5f) * width * 0.8f, (shiftY + 1.5f) * height * 0.8f)
            ),
            blendMode = BlendMode.Screen
        )

        // Evaluación de patrones según la configuración del ticket
        when (config.patternType) {
            FoilPatternType.RAINBOW_GLASS -> drawRainbowGlassPattern(config, shiftX, shiftY)
            FoilPatternType.DIAMOND_CRYSTAL -> drawDiamondCrystalPattern(config, shiftX, shiftY)
            FoilPatternType.STAR_FOIL -> drawStarFoilPattern(config, shiftX, shiftY)
            FoilPatternType.LIQUID_OIL_SLATE -> drawLiquidOilSlatePattern(config, shiftX, shiftY)
            FoilPatternType.NONE -> { /* Sin overlay adicional */ }
        }
    }
}


