package com.rogergcc.sampleprojectotter2026.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.rogergcc.sampleprojectotter2026.R

// 1. Fuentes del proyecto
val OswaldFontFamily = FontFamily(
    Font(R.font.oswald_bold, FontWeight.Bold),
    Font(R.font.oswald_regular, FontWeight.Normal)
)

val MontserratFontFamily = FontFamily(
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_black, FontWeight.Black)
)

// 2. Definición centralizada de TextStyles predeterminados para Tickets
object TicketTypography {
    val TourSubtitle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.5.sp,
        fontFamily = MontserratFontFamily
    )
    val MainTitle = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Black,
        fontFamily = OswaldFontFamily
    )
    val EventDate = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = OswaldFontFamily,
        lineHeight = 0.75.em,
    )
    val Venue = TextStyle(
        fontSize = 29.5.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = OswaldFontFamily,
        // letter-spacing: -2px;
        letterSpacing = (-1.5).sp,
        // line-height: 0.05; (0.05 * 20sp = 1sp)
        // 0.75.em junta las dos líneas al máximo sin colapsar el cálculo del contenedor
        lineHeight = 0.75.em,

    )
    val City = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = OswaldFontFamily
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)