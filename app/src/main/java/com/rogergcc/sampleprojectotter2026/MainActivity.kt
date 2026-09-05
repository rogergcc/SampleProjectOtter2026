package com.rogergcc.sampleprojectotter2026

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.rogergcc.sampleprojectotter2026.ui.composables.CommemorativeTicketCard
import com.rogergcc.sampleprojectotter2026.ui.composables.rememberTicketSensors
import com.rogergcc.sampleprojectotter2026.ui.helpers.angledGradient
import com.rogergcc.sampleprojectotter2026.ui.model.FoilPatternType
import com.rogergcc.sampleprojectotter2026.ui.model.TicketCutStyle
import com.rogergcc.sampleprojectotter2026.ui.model.TicketHologramConfig
import com.rogergcc.sampleprojectotter2026.ui.model.TicketModel
import com.rogergcc.sampleprojectotter2026.ui.model.TicketStyleConfig
import com.rogergcc.sampleprojectotter2026.ui.model.TopCutType
import com.rogergcc.sampleprojectotter2026.ui.theme.AirbagGradientEnd
import com.rogergcc.sampleprojectotter2026.ui.theme.AirbagGradientStart
import com.rogergcc.sampleprojectotter2026.ui.theme.AirbagPrimary
import com.rogergcc.sampleprojectotter2026.ui.theme.AirbagSecondary
import com.rogergcc.sampleprojectotter2026.ui.theme.AirbagTextLight
import com.rogergcc.sampleprojectotter2026.ui.theme.GorillazGradientEnd
import com.rogergcc.sampleprojectotter2026.ui.theme.GorillazGradientStart
import com.rogergcc.sampleprojectotter2026.ui.theme.GorillazPrimary
import com.rogergcc.sampleprojectotter2026.ui.theme.GorillazSecondary
import com.rogergcc.sampleprojectotter2026.ui.theme.GorillazTextLight
import com.rogergcc.sampleprojectotter2026.ui.theme.SampleProjectOtter2026Theme
import com.rogergcc.sampleprojectotter2026.ui.theme.TicketTypography

// 1. Definis el modelo totalmente parametrizado
val gorillazTicketConfig = TicketModel(
    mainTitleText = "GORILLAZ",
    subtitleText = "THE MOUNTAIN TOUR 2026",
    dateText = "LUNES 23 DE NOVIEMBRE",
    venueText = "ARENA 1 PARK",
    cityText = "LIMA, PERÚ",
//    backgroundBrush = Brush.verticalGradient(
//        colors = listOf(
//            GorillazGradientStart,
//            GorillazGradientEnd)
//    ),
    backgroundBrush = angledGradient(
        degrees = 90f,
        0f to GorillazGradientStart,
        1f to GorillazGradientEnd
    ),

    artistLogoRes = R.drawable.ic_logo_gorillaz,
    illustrationRes = R.drawable.ic_banda_gorillaz_footer,
    cutStyle = TicketCutStyle(
        topCutType = TopCutType.CONCAVE_TEETH,
        topTeethCount = 10,       // Cantidad ideal de troqueles
        topCutDepth = 33f,        // Profundidad ajustada para evitar cortes distorsionados
        teethSpacing = 16f,        // Espaciado plano entre muescas
        cornerCutRadius = 24f, // Ajustable según el ticket impreso
        showSideNotches = true,
        sideNotchRadius = 33f

    ),
    styleConfig = TicketStyleConfig(
        tourTitleStyle = TicketTypography.TourSubtitle.copy(color = GorillazPrimary),
        dateStyle = TicketTypography.EventDate.copy(color = GorillazSecondary),
        venueStyle = TicketTypography.Venue.copy(color = GorillazTextLight),
        cityStyle = TicketTypography.City.copy(color = GorillazTextLight)
    ),

    hologramConfig = TicketHologramConfig(
        patternType = FoilPatternType.STAR_FOIL,
        baseAlpha = 0.15f, // Aumenta a 0.45f (0.10f es demasiado bajo para este PNG)
        customFoilColors = listOf(
            Color(0xFF570202),
            Color(0xFF77002B),
            Color(0x88050505)),
        textureRes = R.drawable.frame_project_base_5
    )
)


val blackEyedPeasTicketConfig = TicketModel(
    mainTitleText = "BLACK EYED PEAS",
    subtitleText = "ELEVATION WORLD TOUR 2026",
    dateText = "VIERNES 18 DE DICIEMBRE",
    venueText = "ESTADIO NACIONAL",
    cityText = "LIMA, PERÚ",
    artistLogoRes = R.drawable.bep_logo,
    illustrationRes = R.drawable.bep_footer_9,
    // Propuesta de mejora en paleta

    backgroundBrush = angledGradient(
        degrees = 180f,
        0.0f to Color(0xFF5703AD), // Púrpura eléctrico profundo
        0.5f to Color(0xFF4E289F), // Violeta vibrante
    ),
    styleConfig = TicketStyleConfig(
        tourTitleStyle = TicketTypography.TourSubtitle.copy(color = AirbagPrimary),
        dateStyle = TicketTypography.EventDate.copy(color = AirbagSecondary),
        venueStyle = TicketTypography.Venue.copy(color = AirbagTextLight),
        cityStyle = TicketTypography.City.copy(color = AirbagTextLight)
    ),
    cutStyle = TicketCutStyle(
        topCutType = TopCutType.CONCAVE_TEETH,
        topTeethCount = 10,       // Cantidad ideal de troqueles
        topCutDepth = 33f,        // Profundidad ajustada para evitar cortes distorsionados
        teethSpacing = 16f,        // Espaciado plano entre muescas
        cornerCutRadius = 24f, // Ajustable según el ticket impreso
        showSideNotches = true,
        sideNotchRadius = 33f

    ),
    hologramConfig = TicketHologramConfig(
        patternType = FoilPatternType.STAR_FOIL,
        baseAlpha = 0.15f, // Aumenta a 0.45f (0.10f es demasiado bajo para este PNG)
        customFoilColors = listOf(
            Color(0xFF570202),
            Color(0xFF77002B),
            Color(0x88050505)),
        textureRes = R.drawable.frame_project_base_5
    ),

)

val airbagTicketConfig2 = TicketModel(
    mainTitleText = "AIRBAG",
    subtitleText = "THE AIRBAG TOUR 2026",
    dateText = "Viernes 28 de Agosto 2026",
    venueText = "Estadio Joel Gutiérrez",
    cityText = "Tacna",
    backgroundBrush = Brush.verticalGradient(
        colors = listOf(AirbagGradientStart, AirbagGradientEnd)
    ),
    artistLogoRes = R.drawable.airbag_logo,
    illustrationRes = R.drawable.airbag_footer_3,
    cutStyle = TicketCutStyle(
        topCutType = TopCutType.CONCAVE_TEETH,
        topTeethCount = 10,       // Cantidad ideal de troqueles
        topCutDepth = 33f,        // Profundidad ajustada para evitar cortes distorsionados
        teethSpacing = 16f,        // Espaciado plano entre muescas
        cornerCutRadius = 24f, // Ajustable según el ticket impreso
        showSideNotches = true,
        sideNotchRadius = 33f

    ),
    styleConfig = TicketStyleConfig(
        tourTitleStyle = TicketTypography.TourSubtitle.copy(color = AirbagPrimary),
        dateStyle = TicketTypography.EventDate.copy(color = AirbagSecondary),
        venueStyle = TicketTypography.Venue.copy(color = AirbagTextLight),
        cityStyle = TicketTypography.City.copy(color = AirbagTextLight)
    ),
    hologramConfig = TicketHologramConfig(
        patternType = FoilPatternType.RAINBOW_GLASS,
        baseAlpha = 0.20f,
        customFoilColors = listOf(Color(0xFF01117A), Color(0xFF000F50), Color(0x88100D0D)),
//        textureRes = R.drawable.circle_donw
    )
)

fun obtainRandomTicketConfig(): TicketModel {
    val ticketOptions = listOf(
        gorillazTicketConfig,
        blackEyedPeasTicketConfig,
        airbagTicketConfig2
    )
    return ticketOptions.random()
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // 1. Observamos el estado del giroscopio
            val sensorState by rememberTicketSensors()
            // ✅ CORRECTO: Se ejecuta una sola vez cuando el Composable entra en la composición
//            val randomTicket = remember { obtainRandomTicketConfig() }

            // 2. Lista ordenada de tus configuraciones
            val ticketList = remember {
                listOf(
                    gorillazTicketConfig,
                    blackEyedPeasTicketConfig,
                    airbagTicketConfig2
                )
            }
            // 3. Estado para controlar el índice actual (Inicia en 0)
            var currentIndex by remember { mutableStateOf(0) }

            // 4. Obtenemos el ticket según el índice actual
            val currentTicket = ticketList[currentIndex]

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF121212))
                    .clickable {
                        // Incrementa el índice y reinicia a 0 al llegar al final
                        currentIndex = (currentIndex + 1) % ticketList.size
                    },
                contentAlignment = Alignment.Center

            ) {
                // Pasamos las lecturas como lambdas para deferir la ejecución a la Fase de Draw
                CommemorativeTicketCard(
                    ticket = currentTicket,
                    pitchProvider = { sensorState.pitch },
                    rollProvider = { sensorState.roll }
                )
            }
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
                ticket = airbagTicketConfig2,
                pitchProvider = { sensorState.pitch },
                rollProvider = { sensorState.roll }
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
