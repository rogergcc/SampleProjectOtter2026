package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.theme.OswaldFontFamily

// 1. Modelo de Datos Configurable
data class MechaCardStyle(
    val title: String = "RX-93",
    val subtitle: String = "GUNDAM",
    val category: String = "E.F.S.F",
    val footerText: String = "EARTH - FEDERATION - SPACE - FORCE",
    val japaneseTag: String = "ボクシー",
    val primaryColor: Color = Color(0xFF2B6CB0),
    val accentColor: Color = Color(0xFFE53E3E),
    val imageRes: Int = R.drawable.img_1 // Cambiable por URL o Recurso Local
)

// 2. Componente de la Tarjeta
@Composable
fun TacticalMechaCard(
    config: MechaCardStyle,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(320.dp)
            .height(240.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = config.primaryColor)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // 1. PRIMERO: Franja blanca (se dibuja al fondo)
            // 1. Capa Fondo: Franja blanca
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.18f)
                    .align(Alignment.TopCenter)
                    .offset(y = 12.dp)
                    .background(Color.White)
            )
            // 2. Capa Media: Ilustración
            Image(
                painter = painterResource(id = config.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.BottomStart)
            )

            // --- TEXTOS CONFIGURABLES (Lado Derecho) ---
            // 3. Capa Frontal: Textos (Naturalmente encima sin necesidad de zIndex)
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp, top = 20.dp)
                    .zIndex(1f), // Fuerza a renderizar los textos sobre la imagen
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = config.category,
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    fontFamily = OswaldFontFamily
                )
                Text(
                    text = config.subtitle,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = OswaldFontFamily
                )
                Text(
                    text = config.title,
                    color = Color.White,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 42.sp,
                    fontFamily = OswaldFontFamily
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = config.footerText,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    fontFamily = OswaldFontFamily
                )
            }



            // --- ETIQUETA JAPONESA INFERIOR ---
            Text(
                text = config.japaneseTag,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = OswaldFontFamily,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp)
            )
        }
    }
}

@Preview
@Composable
fun TacticalMechaCardPreview() {
    TacticalMechaCard(
        config = MechaCardStyle(
            title = "RX-93",
            subtitle = "GUNDAM",
            category = "E.F.S.F",
            footerText = "EARTH - FEDERATION - SPACE - FORCE",
            japaneseTag = "1988",
            primaryColor = Color(0xFF2B6CB0),
            accentColor = Color(0xFFE53E3E),
            imageRes = R.drawable.gundam_iloveimg_remove
        )
    )
}