package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.rogergcc.sampleprojectotter2026.R

@Composable
fun OrganicBirdMaskCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(250.dp)
//            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // La imagen aplicando la máscara orgánica directa
        Image(
            painter = painterResource(id = R.drawable.gouldian_finch_illustration), // Tu imagen del panel de pájaros
            contentDescription = "Pájaro con fondo de jungla",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                // AQUÍ SE APLICA EL EFECTO:
                .clip(CustomOrganicShape2())
        )
    }
}

// --- CLASE QUE CREA LA MÁSCARA ORGÁNICA PERSONALIZADA ---
class CustomOrganicShape2 : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val w = size.width
            val h = size.height

            // Punto inicial (Arriba)
            moveTo(w * 0.5f, 0f)

            // Curva 1: De arriba a la derecha (Pico superior derecho)
            cubicTo(
                w * 0.85f, h * 0.05f,
                w * 1.05f, h * 0.35f,
                w * 0.9f, h * 0.65f
            )

            // Curva 2: Hacia abajo a la derecha (Entrada hacia adentro)
            cubicTo(
                w * 0.8f, h * 0.85f,
                w * 0.65f, h * 1.05f,
                w * 0.4f, h * 0.95f
            )

            // Curva 3: Hacia la izquierda (Pico inferior izquierdo)
            cubicTo(
                w * 0.15f, h * 0.9f,
                -w * 0.1f, h * 0.6f,
                w * 0.1f, h * 0.35f
            )

            // Curva 4: De regreso al punto inicial (Cierra la forma)
            cubicTo(
                w * 0.2f, h * 0.15f,
                w * 0.3f, 0f,
                w * 0.5f, 0f
            )

            close()
        }
        return Outline.Generic(path)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrganicBirdMaskCard() {
    OrganicBirdMaskCard()
}
