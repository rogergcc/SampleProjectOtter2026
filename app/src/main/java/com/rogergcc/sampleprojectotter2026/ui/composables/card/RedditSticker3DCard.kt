package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun RedditSticker3DCard(
    badgeName: String,
    badgeDescription: String,
    modifier: Modifier = Modifier
) {
    // Control de arrastre para simular la inclinación 3D del contenedor completo
    var dragX by remember { mutableStateOf(0f) }
    var dragY by remember { mutableStateOf(0f) }

    // Animación de retorno suave (Inercia física) al soltar el sticker
    val animatedDragX by animateFloatAsState(targetValue = dragX, animationSpec = tween(150), label = "X")
    val animatedDragY by animateFloatAsState(targetValue = dragY, animationSpec = tween(150), label = "Y")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1B)), // Fondo oscuro de Reddit
        contentAlignment = Alignment.Center
    ) {
        // TARJETA CONTENEDORA (Base de la tarjeta/Libreta de stickers)
        Card(
            modifier = Modifier
                .width(300.dp)
                .height(400.dp)
                .graphicsLayer {
                    // Rotación sutil en 3D de la tarjeta base
                    rotationX = -animatedDragY * 0.05f
                    rotationY = animatedDragX * 0.05f
                    cameraDistance = 12f
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            dragX = (dragX + dragAmount.x).coerceIn(-150f, 150f)
                            dragY = (dragY + dragAmount.y).coerceIn(-150f, 150f)
                        },
                        onDragEnd = {
                            dragX = 0f
                            dragY = 0f
                        }
                    )
                },
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF272729)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                
                // Fondo con patrón geométrico sutil (Representando el background del Recap)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Color(0xFF3E3E42), Color(0xFF272729)),
                                radius = 600f
                            )
                        )
                )

                // CAPA 1: EL STICKER FLOTANTE (Paralaje 3D)
                // Se mueve en la misma dirección del dedo pero con mayor intensidad multiplicadora,
                // simulando que está despegado físicamente del fondo.
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset {
                            IntOffset(
                                x = (animatedDragX * 0.25f).roundToInt(), 
                                y = (animatedDragY * 0.25f).roundToInt()
                            )
                        }
                        .graphicsLayer {
                            // Rotación agresiva del sticker independiente de la tarjeta base
                            rotationX = -animatedDragY * 0.12f
                            rotationY = animatedDragX * 0.12f
                            cameraDistance = 10f
                        }
                        // Sombra dinámica: se desplaza en dirección opuesta al sticker para acentuar el 3D
                        .shadow(
                            elevation = 16.dp,
                            shape = CircleShape,
                            clip = false,
                            ambientColor = Color.Black,
                            spotColor = Color.Black.copy(alpha = 0.8f)
                        )
                        .size(160.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xFFFF4500), Color(0xFFFF8700)) // Degradado Naranja Reddit
                            )
                        )
                        // Borde blanco grueso característico de un sticker troquelado de vinilo
                        .border(4.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Icono central / Avatar del Sticker
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Icono Sticker",
                        tint = Color.White,
                        modifier = Modifier.size(70.dp)
                    )
                }

                // CAPA 2: TEXTO INFERIOR (Fijo en la tarjeta)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp, start = 24.dp, end = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = badgeName,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = badgeDescription,
                        color = Color.LightGray,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RedditSticker3DCardPreview() {
    RedditSticker3DCard(
        badgeName = "Karma Champion",
        badgeDescription = "Obtuviste este sticker tras desbloquear los logros de comunidad de nivel Platino."
    )
}
