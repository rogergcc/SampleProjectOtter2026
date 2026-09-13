package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Reddit3DRecapCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    // Estados para controlar la inclinación en los ejes X e Y al arrastrar
    var rotateX by remember { mutableStateOf(0f) }
    var rotateY by remember { mutableStateOf(0f) }

    // Suavizado de la animación de regreso al centro cuando el usuario suelta la tarjeta
    val animatedRotateX by animateFloatAsState(
        targetValue = rotateX,
        animationSpec = tween(durationMillis = 150),
        label = "RotationX"
    )
    val animatedRotateY by animateFloatAsState(
        targetValue = rotateY,
        animationSpec = tween(durationMillis = 150),
        label = "RotationY"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F1416)), // Fondo oscuro oficial de la app
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(280.dp)
                .height(420.dp)
                // 1. EFECTO 3D: Aplicamos rotaciones y perspectiva espacial
                .graphicsLayer(
                    rotationX = animatedRotateX,
                    rotationY = animatedRotateY,
                    cameraDistance = 16f // Controla la intensidad del efecto de profundidad 3D
                )
                // 2. GESTOS: Capturamos el arrastre para mover los ejes
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            // Limitamos la rotación máxima a 25 grados para mantener la legibilidad
                            rotateX = (rotateX - dragAmount.y * 0.15f).coerceIn(-25f, 25f)
                            rotateY = (rotateY + dragAmount.x * 0.15f).coerceIn(-25f, 25f)
                        },
                        onDragEnd = {
                            // Al soltar, la tarjeta vuelve automáticamente a su posición plana
                            rotateX = 0f
                            rotateY = 0f
                        }
                    )
                },
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
        ) {
            // Diseño interno de la tarjeta con gradiente Reddit
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFFEC008C), Color(0xFFFC6767)) // Gradiente neón de fin de año
                        )
                    )
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "REDDIT RECAP",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp
                    )

                    // Zona central para la ilustración tridimensional
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        // Aquí se renderizaban los avatares e infografías festivas
                        Text(
                            text = "🔮",
                            fontSize = 80.sp
                        )
                    }

                    Column {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 28.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = subtitle,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Reddit3DRecapCardPreview() {
    Reddit3DRecapCard(
        title = "Explorador de Mentes",
        subtitle = "Pasaste el 45% de tu tiempo leyendo discusiones complejas en r/androiddev."
    )
}
