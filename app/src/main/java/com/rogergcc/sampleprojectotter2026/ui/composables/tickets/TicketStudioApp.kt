package com.rogergcc.sampleprojectotter2026.ui.composables.tickets

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
import com.rogergcc.sampleprojectotter2026.ui.airbagTicketConfig2
import com.rogergcc.sampleprojectotter2026.ui.blackEyedPeasTicketConfig
import com.rogergcc.sampleprojectotter2026.ui.composables.CommemorativeTicketCard
import com.rogergcc.sampleprojectotter2026.ui.composables.TicketPreviewCaptureBox
import com.rogergcc.sampleprojectotter2026.ui.composables.rememberSimulatedOrRealSensors
import com.rogergcc.sampleprojectotter2026.ui.composables.rememberTicketSensors
import com.rogergcc.sampleprojectotter2026.ui.gorillazTicketConfig
import com.rogergcc.sampleprojectotter2026.ui.model.TicketModel


/**
 * Created on septiembre.
 * year 2026 .
 */
@Composable
fun TicketStudioApp(
    ticket: TicketModel,
    deepLinkUri: Uri?,
    onExportRequested: (android.graphics.Bitmap) -> Unit,
) {
    var isAutoAnimate by remember { mutableStateOf(true) }

    // Extracción de parámetros desde el Deep Link
    val fanParam = deepLinkUri?.getQueryParameter("fan") ?: "CARLOS MENDOZA"
    val zoneParam = deepLinkUri?.getQueryParameter("zone") ?: "CAMPO A - VIP"
    val folioParam = deepLinkUri?.getQueryParameter("folio") ?: "#GTZ-2026-0001"

    val currentTicket = remember(fanParam, zoneParam, folioParam) {
        ticket.copy(
            fanName = fanParam,
            zoneText = zoneParam,
            folioCode = folioParam
        )
    }

    val sensorState by rememberTicketSensors()
    val activeRotation by rememberSimulatedOrRealSensors(
        isAutoAnimate = isAutoAnimate,
        realPitch = sensorState.pitch,
        realRoll = sensorState.roll
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentAlignment = Alignment.Center
    ) {
        TicketPreviewCaptureBox(
            onBitmapExported = onExportRequested
        ) { graphicsLayer ->
            CommemorativeTicketCard(
                modifier = Modifier.drawWithContent {
                    graphicsLayer.record { this@drawWithContent.drawContent() }
                    drawContent()
                },
                ticket = currentTicket,
                pitchProvider = { activeRotation.first },
                rollProvider = { activeRotation.second }
            )
        }

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
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun CollectionTickets(
    deepLinkUri: Uri?,
    onExportRequested: (android.graphics.Bitmap) -> Unit,
) {

    val misDisenos: List<@Composable () -> Unit> = listOf(
        {
            TicketStudioApp(
                ticket = gorillazTicketConfig,
                deepLinkUri = deepLinkUri,
                onExportRequested = onExportRequested
            )
        },
        {
            TicketStudioApp(
                ticket = airbagTicketConfig2.copy(
                    fanName = "XXX MENDOZA",
                    zoneText = "CAMPO A - VIP",
                    folioCode = "#GTZ-2026-0001"
                ),
                deepLinkUri = deepLinkUri,
                onExportRequested = onExportRequested
            )
        },
        {
            TicketStudioApp(
                ticket = blackEyedPeasTicketConfig.copy(
                    fanName = "GA GARCÍA",
                    zoneText = "CAMPO B - GENERAL",
                    folioCode = "#GTZ-2026-0002"
                ),
                deepLinkUri = deepLinkUri,
                onExportRequested = onExportRequested
            )
        }
    )
    val pagerState = rememberPagerState(pageCount = { misDisenos.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Fondo oscuro de pasarela
    ) {
        // 2. El Pager ocupa TODA la pantalla
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Ejecutamos el composable que toca en esta página
                misDisenos[page]()
            }
        }

        // 3. Indicador de puntitos flotando abajo
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(misDisenos.size) { index ->
                val activo = pagerState.currentPage == index
                // Cerrar el paréntesis directamente, sin llaves al final
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (activo) 9.dp else 6.dp)
                        .background(
                            color = if (activo) Color.Cyan else Color.DarkGray,
                            shape = CircleShape
                        )
                )
            }
        }
    }

}