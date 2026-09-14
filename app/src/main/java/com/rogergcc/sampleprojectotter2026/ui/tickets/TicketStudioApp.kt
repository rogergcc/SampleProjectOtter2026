package com.rogergcc.sampleprojectotter2026.ui.tickets

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Created on septiembre.
 * year 2026 .
 */


@Composable
fun CollectionTickets(
    deepLinkUri: Uri?,
    onExportRequested: (Bitmap) -> Unit,
    modifier: Modifier = Modifier
) {
    // 1. Extraer parámetros del Deep Link una sola vez
    val fanParam = deepLinkUri?.getQueryParameter("fan") ?: "CARLOS MENDOZA"
    val zoneParam = deepLinkUri?.getQueryParameter("zone") ?: "CAMPO A - VIP"
    val folioParam = deepLinkUri?.getQueryParameter("folio") ?: "#GTZ-2026-0001"

    // 2. Lista de configuraciones estáticas
    val tickets = remember(fanParam, zoneParam, folioParam) {
        listOf(
            gorillazTicketConfig.copy(fanName = fanParam, zoneText = zoneParam, folioCode = folioParam),
            airbagTicketConfig2.copy(fanName = fanParam, zoneText = "CAMPO A - VIP", folioCode = "#GTZ-2026-0001"),
            blackEyedPeasTicketConfig.copy(fanName = fanParam, zoneText = "CAMPO B - GENERAL", folioCode = "#GTZ-2026-0002")
        )
    }

    val pagerState = rememberPagerState(pageCount = { tickets.size })
    var isAutoAnimate by remember { mutableStateOf(true) }

    // 3. Sensor ÚNICO compartido para la pasarela activa
    val sensorState by rememberTicketSensors()
    val activeRotation by rememberSimulatedOrRealSensors(
        isAutoAnimate = isAutoAnimate,
        realPitch = sensorState.pitch,
        realRoll = sensorState.roll
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        TicketPreviewCaptureBox(onBitmapExported = onExportRequested) { graphicsLayer ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CommemorativeTicketCard(
                        modifier = Modifier.drawWithContent {
                            graphicsLayer.record { this@drawWithContent.drawContent() }
                            drawContent()
                        },
                        ticket = tickets[page],
                        pitchProvider = { activeRotation.first },
                        rollProvider = { activeRotation.second }
                    )
                }
            }
        }

        // Indicador de Puntos (Dots Indicator)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 60.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(tickets.size) { index ->
                val active = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (active) 9.dp else 6.dp)
                        .background(
                            color = if (active) Color.Cyan else Color.DarkGray,
                            shape = CircleShape
                        )
                )
            }
        }

        // Switch de Giro/Sensor
        FilterChip(
            selected = isAutoAnimate,
            onClick = { isAutoAnimate = !isAutoAnimate },
            label = {
                Text(
                    text = if (isAutoAnimate) "Modo: Auto Giro" else "Modo: Sensor Real",
                    color = Color.White
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 12.dp)
        )
    }
}