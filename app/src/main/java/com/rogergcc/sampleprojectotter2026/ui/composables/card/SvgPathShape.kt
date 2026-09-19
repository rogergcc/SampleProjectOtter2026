package com.rogergcc.sampleprojectotter2026.ui.composables.card

import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.PathParser
import com.rogergcc.sampleprojectotter2026.R

class SvgPathShape(
    private val pathData: String,
    private val viewportWidth: Float = 200f,
    private val viewportHeight: Float = 200f
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        // 1. Parsear el path SVG
        val androidPath = PathParser.createPathFromPathData(pathData)

        // 2. Calcular los bordes reales del Path
        val pathBounds = RectF()
        androidPath.computeBounds(pathBounds, true)

        // 3. Crear matriz y ajustar origen + escala
        val matrix = Matrix().apply {
            // A. Mover el Path al punto (0,0) para eliminar desfases de margen
            postTranslate(-pathBounds.left, -pathBounds.top)

            // B. Escalar la figura proporcionalmente según el viewport del SVG
            val scaleX = size.width / if (pathBounds.width() > 0) pathBounds.width() else viewportWidth
            val scaleY = size.height / if (pathBounds.height() > 0) pathBounds.height() else viewportHeight

            postScale(scaleX, scaleY)
        }

        // 4. Aplicar transformaciones
        androidPath.transform(matrix)

        // 5. Retornar como Outline
        return Outline.Generic(androidPath.asComposePath())
    }
}

@Preview(showBackground = true)
@Composable
fun SvgPathShapePreview() {
    val organicMaskData = "M162.6,76.9C170.3,103.3 158.2,133.5 134.8,151.3C111.3,169.2 76.4,174.9 53.4,159.3C30.4,143.8 19.3,107 29.1,77.7C38.8,48.5 69.4,26.7 98.4,27.2C127.5,27.7 154.9,50.5 162.6,76.9Z"

    Box(
        modifier = Modifier
            .size(250.dp) // Contenedor cuadrado
            .clip(SvgPathShape(organicMaskData, viewportWidth = 200f, viewportHeight = 200f))
    ) {
        Image(
            painter = painterResource(R.drawable.gouldian_finch_illustration),
            contentDescription = null,
            contentScale = ContentScale.Crop, // Llena el contenedor sin achatarse
            modifier = Modifier.matchParentSize()
        )
    }
}

@Composable
fun PromoCouponCard(
    couponPathData: String,
    onClaimClick: () -> Unit
) {
    val shape = SvgPathShape(couponPathData, viewportWidth = 200f, viewportHeight = 200f)

    Box(
        modifier = Modifier
            .size(width = 280.dp, height = 160.dp)
            // 1. Sombra 3D real con la silueta exacta de la mancha
            .shadow(elevation = 8.dp, shape = shape, spotColor = Color(0xFF6200EE))
            .clip(shape)
            .background(Color(0xFF6200EE))
            // 2. Solo detecta clics DENTRO del área visible del cupón
            .clickable { onClaimClick() }
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("¡50% OFF!", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Toca para canjear", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
        }
    }
}

@Composable
fun OrganicActionButton(
    svgPathData: String,
    onClick: () -> Unit
) {
    val buttonShape = SvgPathShape(svgPathData, viewportWidth = 200f, viewportHeight = 200f)

    Box(
        modifier = Modifier
            .size(80.dp)
            // 1. Borde de 3.dp dibujado exactamente sobre el trazo orgánico
            .border(width = 3.dp, color = Color.Cyan, shape = buttonShape)
            .shadow(elevation = 6.dp, shape = buttonShape)
            .clip(buttonShape)
            .background(Color(0xFF1E1E2E))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Jugar",
            tint = Color.Cyan,
            modifier = Modifier.size(36.dp)
        )
    }
}

val OrganicBlobShape = SvgPathShape(
    pathData = "M162.6,76.9C170.3,103.3 158.2,133.5 134.8,151.3C111.3,169.2 76.4,174.9 53.4,159.3...",
    viewportWidth = 200f,
    viewportHeight = 200f
)

@Composable
fun CustomMaterialDialog() {
    // Usamos el SvgPathShape directamente dentro de componentes oficiales de Material 3
    Card(
        shape = OrganicBlobShape, // Material 3 maneja automáticamente el clip, la elevación y la sombra
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D44)),
        modifier = Modifier.size(240.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Diálogo Orgánico",
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}


// Path orgánico reutilizable de ejemplo (200x200)
//private const val ORGANIC_BLOB_PATH = "M162.6,76.9C170.3,103.3 158.2,133.5 134.8,151.3C111.3,169.2 76.4,174.9 53.4,159.3C30.4,143.8 19.3,107 29.1,77.7C38.8,48.5 69.4,26.7 98.4,27.2C127.5,27.7 154.9,50.5 162.6,76.9Z"
// 1. Rectángulo suave e imperfecto
val ORGANIC_RECT_1_PATH = "M 20 40 C 20 20, 40 15, 100 20 C 160 25, 180 20, 180 40 C 185 90, 180 150, 180 170 C 180 185, 160 180, 100 185 C 40 180, 20 185, 20 170 C 15 110, 20 80, 20 40 Z"
// --- PREVIEW CASO 1: TARJETA PROMOCIONAL CON SOMBRA 3D ---
@Preview(showBackground = true, name = "1. Cupón con Sombra Orgánica")
@Composable
fun PreviewPromoCouponCard() {
    val shape = SvgPathShape(ORGANIC_RECT_12_PATH, viewportWidth = 200f, viewportHeight = 10f)

    Box(
        modifier = Modifier
            .padding(24.dp)
            .size(width = 240.dp, height = 90.dp)
            // La sombra proyecta la silueta orgánica exacta en la GPU
            .shadow(elevation = 12.dp, shape = shape, spotColor = Color(0xFF6200EE))
            .clip(shape)
            .background(Color(0xFF6200EE))
            .clickable { /* Solo activa clic dentro del área orgánica */ }
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("¡50% OFF!", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Toca para canjear", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
        }
    }
}
val ORGANIC_RECT_12_PATH = "M62.5,9.5c-23.9,1.6 -28.9,2.3 -34.3,4.8 -7.3,3.4 -10.3,8.9 -16.3,30.2 -2.2,7.6 -2.3,9.7 -2.4,44.5 0,20.1 -0.4,45.3 -0.9,56 -1.3,31.1 2,47.2 11.2,55.1 5.1,4.4 9.9,5.9 22.2,7 13.1,1.2 50.5,1.1 81,-0.2 14,-0.6 56.6,-1.4 94.5,-1.7 68.1,-0.7 69.1,-0.7 73,-2.9 9.1,-4.9 15.7,-14.5 17.6,-25.7 0.8,-5.1 1,-16.5 0.5,-41.1 -1.6,-75.1 -2.4,-99.2 -3.4,-103 -3.6,-14.1 -8.4,-20.2 -18,-22.9 -5.2,-1.4 -16,-1.6 -105.8,-1.5 -67.8,0.1 -106,0.6 -118.9,1.4z"
// --- PREVIEW CASO 2: BOTÓN DE ACCIÓN CON BORDE NEÓN ---
@Preview(showBackground = true, name = "2. Botón con Borde que sigue el Path")
@Composable
fun PreviewOrganicActionButton() {
    val buttonShape = SvgPathShape(ORGANIC_RECT_12_PATH, viewportWidth = 200f, viewportHeight = 200f)

    Box(
        modifier = Modifier
            .padding(24.dp)
            .size(100.dp)
            // El borde de 3.dp sigue las curvas exactas del SVG
            .border(width = 3.dp, color = Color.Cyan, shape = buttonShape)
            .shadow(elevation = 8.dp, shape = buttonShape, spotColor = Color.Cyan)
            .clip(buttonShape)
            .background(Color(0xFF1E1E2E))
            .clickable { /* Ripple effect dentro del Path */ },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Jugar",
            tint = Color.Cyan,
            modifier = Modifier.size(40.dp)
        )
    }
}

// --- PREVIEW CASO 3: INTEGRACIÓN CON MATERIAL DESIGN 3 ---
@Preview(showBackground = true, name = "3. Card de Material 3 con SvgPathShape")
@Composable
fun PreviewCustomMaterialDialog() {
    val organicShape = SvgPathShape(ORGANIC_RECT_1_PATH, viewportWidth = 200f, viewportHeight = 200f)

    Box(
        modifier = Modifier.padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        // Se pasa la forma directamente a la Card oficial de Material 3
        Card(
            shape = organicShape,
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D44)),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier.size(200.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Diálogo M3",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}