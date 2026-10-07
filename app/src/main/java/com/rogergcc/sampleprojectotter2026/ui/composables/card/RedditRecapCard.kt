package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RedditRecapCard(
    username: String,
    recapYear: String = "2024",
    abilityName: String,
    abilityDescription: String,
    rarityTier: String, // "RARE", "EPIC", "LEGENDARY"
    topSubreddits: List<String>,
    modifier: Modifier = Modifier
) {
    // Definición de gradientes según la rareza (Fondo estilo carta coleccionable)
    val cardGradient = when (rarityTier.uppercase()) {
        "LEGENDARY" -> Brush.verticalGradient(colors = listOf(Color(0xFFFFD700), Color(0xFFFF4500))) // Oro a Naranja
        "EPIC" -> Brush.verticalGradient(colors = listOf(Color(0xFF9400D3), Color(0xFF4B0082)))      // Púrpura épico
        else -> Brush.verticalGradient(colors = listOf(Color(0xFF00FFFF), Color(0xFF1E90FF)))        // Azul raro/estándar
    }

    Card(
        modifier = modifier
            .width(320.dp)
            .height(480.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(cardGradient)
                .padding(16.dp)
        ) {
            // Marco interno decorativo característico de la tarjeta de Reddit
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Color.White.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    
                    // 1. CABECERA: Logo / Año y Username
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "RECAP $recapYear",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp
                        )
                        Text(
                            text = "u/$username",
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // 2. CENTRO: Ilustración / Avatar Espacio
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        // Aquí iría el Snoo Avatar. Dibujamos un marcador de posición limpio.
                        Canvas(modifier = Modifier.size(60.dp)) {
                            drawCircle(color = Color.White)
                        }
                    }

                    // 3. SECCIÓN INFERIOR: Habilidad y Stats
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.3f))
                            .padding(12.dp)
                    ) {
                        // Etiqueta de Rareza
                        Text(
                            text = rarityTier.uppercase(),
                            color = Color(0xFFFF4500),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color.White)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // Nombre de la Habilidad Especial
                        Text(
                            text = abilityName,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        // Descripción de la Habilidad
                        Text(
                            text = abilityDescription,
                            color = Color.White.copy(alpha = 0.8f),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 4.dp),
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Comunidades más visitadas
                        Text(
                            text = "TOP COMMUNITIES",
                            color = Color.White.copy(alpha = 0.6f),
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        
                        Row(
                            modifier = Modifier.padding(top = 4.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            topSubreddits.forEachIndexed { index, sub ->
                                Text(
                                    text = "r/$sub",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                if (index < topSubreddits.lastIndex) {
                                    Text(
                                        text = " • ",
                                        color = Color.White.copy(alpha = 0.5f),
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RedditRecapCardPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RedditRecapCard(
                username = "AndroidDevSnoo",
                abilityName = "Infinite Compiler",
                abilityDescription = "+50% de velocidad al resolver bugs de recomposición en hilos secundarios.",
                rarityTier = "EPIC",
                topSubreddits = listOf("androiddev", "JetpackCompose", "kotlin")
            )
        }
    }
}
