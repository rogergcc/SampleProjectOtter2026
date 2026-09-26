package com.rogergcc.sampleprojectotter2026.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

/**
 * Adapta la luminosidad de un color base para usarlo como subtítulo.
 * Si el color es claro, lo oscurece. Si es oscuro (Modo Oscuro), lo aclara.
 */
fun Color.toSubtitleColor(factor: Float = 0.3f): Color {
    val isLight = this.luminance() > 0.5f
    return if (isLight) {
        // Oscurecer para contrastar sobre fondo claro
        Color(
            red = (this.red * (1f - factor)).coerceIn(0f, 1f),
            green = (this.green * (1f - factor)).coerceIn(0f, 1f),
            blue = (this.blue * (1f - factor)).coerceIn(0f, 1f),
            alpha = this.alpha
        )
    } else {
        // Aclarar para contrastar sobre fondo oscuro
        Color(
            red = (this.red + (1f - this.red) * factor).coerceIn(0f, 1f),
            green = (this.green + (1f - this.green) * factor).coerceIn(0f, 1f),
            blue = (this.blue + (1f - this.blue) * factor).coerceIn(0f, 1f),
            alpha = this.alpha
        )
    }
}

// 1. Función de extensión para oscurecer cualquier color dinámicamente
fun Color.darken(factor: Float = 0.6f): Color {
    return Color(
        red = this.red * factor,
        green = this.green * factor,
        blue = this.blue * factor,
        alpha = this.alpha
    )
}
