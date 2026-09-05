package com.rogergcc.sampleprojectotter2026.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rogergcc.sampleprojectotter2026.ui.theme.TicketDarkBackground
import com.rogergcc.sampleprojectotter2026.ui.theme.TicketTypography


/**
 * Created on septiembre.
 * year 2026 .
 */

// ==========================================
// 1. MODELOS Y CONFIGURACIONES
// ==========================================
// 1. Manteniendo tus Enums existentes
enum class TopCutType { CONCAVE_TEETH, CONVEX_TEETH, SAW_TEETH, NONE }
enum class FoilPatternType { RAINBOW_GLASS, DIAMOND_CRYSTAL, STAR_FOIL, LIQUID_OIL_SLATE, NONE }

@Immutable
 data class TicketHologramConfig(
    val patternType: FoilPatternType = FoilPatternType.DIAMOND_CRYSTAL,
    @DrawableRes val textureRes: Int? = null,
    val baseAlpha: Float = 0.25f,
    val gyroSensitivity: Float = 20f,
    val overlayIntensity: Float = 0.60f,
    val customFoilColors: List<Color> = emptyList()
)

@Immutable
data class TicketModel(
    val mainTitleText: String,
    val subtitleText: String? = null,
    val dateText: String,
    val venueText: String,
    val cityText: String, // <-- AÑADIR 'val' AQUÍ
    @DrawableRes val artistLogoRes: Int? = null,
    @DrawableRes val illustrationRes: Int? = null,
    val backgroundBrush: Brush,
    val styleConfig: TicketStyleConfig = TicketStyleConfig(),
    val cutStyle: TicketCutStyle = TicketCutStyle(),
    val hologramConfig: TicketHologramConfig = TicketHologramConfig(),
    val zoneText: String? = "CAMPO A - VIP",
    val fanName: String? = "CARLOS MENDOZA",
    val folioText: String? = "#GTZ-2026-00892"
)

@Immutable // Opcional pero recomendado para Jetpack Compose
data class TicketCutStyle(
    val topCutType: TopCutType = TopCutType.CONCAVE_TEETH,
    val topTeethCount: Int = 8,
    val topCutDepth: Float = 22f,
    val teethSpacing: Float = 10f,
    val cornerCutRadius: Float = 16f,
    val showSideNotches: Boolean = true,
    val sideNotchRadius: Float = 20f,
    val sideNotchYRatio: Float = 0.52f,
    val bottomCornerRadius: Float = 24f
)
data class TicketStyleConfig(
    val tourTitleStyle: TextStyle = TicketTypography.TourSubtitle,
    val mainTitleStyle: TextStyle = TicketTypography.MainTitle,
    val dateStyle: TextStyle = TicketTypography.EventDate,
    val venueStyle: TextStyle = TicketTypography.Venue,
    val cityStyle: TextStyle = TicketTypography.City,
    val dividerColor: Color = TicketDarkBackground,
    val dividerThickness: Dp = 2.dp,
    val dividerFraction: Float = 0.9f
)