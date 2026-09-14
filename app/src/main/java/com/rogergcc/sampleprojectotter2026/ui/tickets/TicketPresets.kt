package com.rogergcc.sampleprojectotter2026.ui.tickets

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.rogergcc.sampleprojectotter2026.R
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
import com.rogergcc.sampleprojectotter2026.ui.theme.TicketTypography


/**
 * Created on septiembre.
 * year 2026 .
 */
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
    cutStyle = TicketDefaults.StandardCutStyle, // DRY Aplicado,
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
    cutStyle = TicketDefaults.StandardCutStyle, // DRY Aplicado
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
    cutStyle = TicketDefaults.StandardCutStyle, // DRY Aplicado
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