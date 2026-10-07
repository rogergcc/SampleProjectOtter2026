package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
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
fun GouldianFinchPanel(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp) // Opcional: Define un alto fijo adecuado
    ) {
        // --- PANEL IZQUIERDO ---
        Box(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.gouldian_finch_illustration),
                contentDescription = "Ilustración aves",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // --- PANEL DERECHO CON MÁSCARA ---
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.gouldian_finch_illustration),
                contentDescription = "Ave enfocada",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center, // Enfoca el ave del lado derecho
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleOrganicShape())
            )
        }
    }
}



// --- DEFICIÓN DE LA FORMA PERSONALIZADA PARA LA MÁSCARA ORGÁNICA ---
// Esto es mucho más limpio que definir formas XML raras.
class CircleOrganicShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path()
        // Aquí definimos la forma orgánica como en la imagen.
        // Un Path con arcos y curvas cúbicas.
        val w = size.width
        val h = size.height

        // Punto de inicio y curvas complejas
        path.moveTo(0f, h * 0.4f)
        path.cubicTo(w * 0.2f, h * 0.1f, w * 0.5f, -h * 0.1f, w * 0.8f, h * 0.2f)
        path.cubicTo(w, h * 0.4f, w * 1.1f, h * 0.8f, w * 0.6f, h)
        path.cubicTo(w * 0.4f, h * 1.1f, w * 0.1f, h * 0.9f, 0f, h * 0.6f)
        path.close()

        return Outline.Generic(path)
    }
}

@Preview
@Composable
fun GouldianFinchPreview() {
    GouldianFinchPanel(
        modifier = Modifier
            .padding(16.dp)
    )
}
