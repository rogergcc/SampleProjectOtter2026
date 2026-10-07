package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.preview.PreviewDefault

// --- 1. MODELO DE DATOS (Single Source of Truth) ---
data class AnimeCharacterCardData(
    val titleTop: String,
    val titleBottom: String,
    val characterImageRes: Int,
    val gradientColors: List<Color> = listOf(Color(0xFF8B0000), Color(0xFFD32F2F))
)

// --- 2. COMPOSABLE REUTILIZABLE (KISS & DRY) ---
@Composable
fun CharacterPosterCard(
    data: AnimeCharacterCardData,
    modifier: Modifier = Modifier,
    cardHeight: Dp = 480.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(cardHeight)
            .background(Color.Transparent)
    ) {
        // CAPA 1: Texto alineado arriba (TopCenter) con padding superior
        TextGradientBackground(
            titleTop = data.titleTop,
            titleBottom = data.titleBottom,
            gradientColors = data.gradientColors,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 32.dp) // Controlas la altura exacta donde inicia el texto
        )

        // CAPA 2: Imagen del personaje
        Image(
            painter = painterResource(id = data.characterImageRes),
            contentDescription = "${data.titleTop} ${data.titleBottom}",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
    }
}
// --- 3. COMPONENTE ATÓMICO (SRP) ---
@Composable
private fun TextGradientBackground(
    titleTop: String,
    titleBottom: String,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier
) {
    val textGradientStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Black,
        fontSize = 68.sp,
        lineHeight = 60.sp,
        textAlign = TextAlign.Center,
        brush = Brush.verticalGradient(colors = gradientColors)
    )

    Text(
        text = "$titleTop\n$titleBottom",
        style = textGradientStyle,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun CharacterPosterCardPreview() {
    val sampleData = AnimeCharacterCardData(
        titleTop = "UNICORN",
        titleBottom = "GUNDAM",
        characterImageRes = R.drawable.gundam_2, // Reemplaza por tu recurso en res/drawable
        gradientColors = listOf(
            Color(0xFFA10336),
            Color(0xFFE84305)
        ) // De naranja a rojo
    )
    CharacterPosterCard(
        data = sampleData,
        cardHeight = 500.dp
    )
}

