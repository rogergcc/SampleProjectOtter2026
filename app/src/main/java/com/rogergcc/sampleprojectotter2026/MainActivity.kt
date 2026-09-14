package com.rogergcc.sampleprojectotter2026

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.rogergcc.sampleprojectotter2026.ui.composables.card.CollectionHistoryCard
import com.rogergcc.sampleprojectotter2026.ui.tickets.airbagTicketConfig2
import com.rogergcc.sampleprojectotter2026.ui.tickets.blackEyedPeasTicketConfig
import com.rogergcc.sampleprojectotter2026.ui.tickets.CommemorativeTicketCard
import com.rogergcc.sampleprojectotter2026.ui.tickets.rememberSimulatedOrRealSensors
import com.rogergcc.sampleprojectotter2026.ui.tickets.rememberTicketSensors
import com.rogergcc.sampleprojectotter2026.ui.tickets.CollectionTickets
import com.rogergcc.sampleprojectotter2026.ui.tickets.gorillazTicketConfig
import com.rogergcc.sampleprojectotter2026.ui.saveAndShareScreenshot
import com.rogergcc.sampleprojectotter2026.ui.theme.SampleProjectOtter2026Theme


class MainActivity : ComponentActivity() {

    // 1. Inicializamos la variable leyendo directamente el Intent con el que se abrió la app
    private var deepLinkUri = mutableStateOf<Uri?>(null)

    // Se ejecuta cuando la app YA estaba abierta y tocas un nuevo enlace
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        deepLinkUri.value = intent.data // Actualiza la UI de inmediato
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asignamos el Intent inicial si existe (Apertura desde cero)
        deepLinkUri.value = intent?.data
        enableEdgeToEdge()

        setContent {
//            CollectionTickets(
//                deepLinkUri = deepLinkUri.value,
//                onExportRequested = { bitmap ->
//                    saveAndShareScreenshot(this@MainActivity, bitmap)
//                }
//            )
            CollectionHistoryCard(
                modifier = Modifier
            )


        }
    }
}





@Preview(showBackground = true)
@Composable
fun TicketGorillazPreview() {
    SampleProjectOtter2026Theme {
        // Observamos el estado del giroscopio
        val sensorState by rememberTicketSensors()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)),
            contentAlignment = Alignment.Center
        ) {
            // Pasamos las lecturas como lambdas para deferir la ejecución a la Fase de Draw
            CommemorativeTicketCard(
                ticket = gorillazTicketConfig,
                pitchProvider = { sensorState.pitch },
                rollProvider = { sensorState.roll }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketAirbagPreview() {
    SampleProjectOtter2026Theme {
        // 1. Estado para conmutar entre animación automática y sensor real
        var isAutoAnimate by remember { mutableStateOf(true) }
        // 4. Lectura de sensores reales y simulación animada
        val sensorState by rememberTicketSensors()

        // Observamos el estado del giroscopio
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
            CommemorativeTicketCard(
                ticket = airbagTicketConfig2,
                pitchProvider = { activeRotation.first },
                rollProvider = { activeRotation.second }
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun BlackEyedPeasPreview() {
    SampleProjectOtter2026Theme {
        // Observamos el estado del giroscopio
        val sensorState by rememberTicketSensors()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)),
            contentAlignment = Alignment.Center
        ) {
            // Pasamos las lecturas como lambdas para deferir la ejecución a la Fase de Draw
            CommemorativeTicketCard(
                ticket = blackEyedPeasTicketConfig,
                pitchProvider = { sensorState.pitch },
                rollProvider = { sensorState.roll }
            )
        }
    }
}


@Preview(name = "1. Celular Pequeño (320dp)", widthDp = 320, heightDp = 640, showBackground = true)
@Preview(name = "2. Celular Estándar", showBackground = true)
@Preview(name = "3. Plegable Abierto", device = Devices.FOLDABLE, showBackground = true)
@Preview(name = "4. Tablet Grande", device = Devices.TABLET, showBackground = true)
@Composable
fun TablaControlDeDisenoPreview() {
    SampleProjectOtter2026Theme {
        // Observamos el estado del giroscopio
        val sensorState by rememberTicketSensors()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)),
            contentAlignment = Alignment.Center
        ) {
            // Pasamos las lecturas como lambdas para deferir la ejecución a la Fase de Draw
            CommemorativeTicketCard(
                ticket = gorillazTicketConfig,
                pitchProvider = { sensorState.pitch },
                rollProvider = { sensorState.roll }
            )
        }
    }
}

