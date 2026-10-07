package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.theme.AntonScFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.ArchivoFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.OswaldFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.RubicMonoOneFontFamily

@Composable
fun TextImageMaskCompose(
    modifier: Modifier = Modifier,
    title: String,
    imageRes: Int,
    borderColor: Color = Color.White,
    borderWidth: Float = 12f,
    fontSize: TextUnit = 120.sp,
    fontFamily: FontFamily = OswaldFontFamily,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            // 1. Aislar el renderizado para la máscara
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            // 2. Dibujar el contenido del Text como máscara pura
            .drawWithContent {
                drawContent() // Dibuja el Text() en blanco como silueta
            },
        contentAlignment = Alignment.Center
    ) {

        // --- TEXTO GIGANTE (Máscara) ---
        // --- 1. CAPA DE BORDE EXTERIOR (DETRÁS) ---
        Text(
            text = title,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            style = TextStyle(
                drawStyle = Stroke(width = borderWidth) // Traza solo el contorno limpio
            ),
            color = borderColor,
        )

        // --- 2. CAPA DE IMAGEN MÁSCARA (ENFRENTE) ---
        Box(
            modifier = Modifier
                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                .drawWithContent { drawContent() }
        ) {
            // Texto base para la silueta del recorte
            Text(
                text = title,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.White,
            )

            // La imagen rellenando la silueta
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { blendMode = BlendMode.SrcIn }
            )

        }
    }
}




//Box(
//modifier = modifier
//.fillMaxWidth(),
//contentAlignment = Alignment.TopCenter
//
//){
//    Text(
//        text = "JAPAN",
//        color = Color.White,
//        fontSize = 12.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily =ArchivoFontFamily,
//        letterSpacing = 4.sp,
//
//        modifier = Modifier
//            .padding(horizontal = 6.dp, vertical = 2.dp)
//    )
//
//}

@Composable
fun TextImageMaskPoster(
    modifier: Modifier = Modifier,
    topTag: String = "J A P A N",
    title: String = "TOKYO",
    subTitle: String = "LAND OF THE RISING SUN",
    imageRes: Int,
    borderColor: Color = Color.White,
    borderWidth: Float = 12f,
    fontSize: TextUnit = 110.sp,
    fontFamily: FontFamily = OswaldFontFamily,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black) // Fondo oscuro estilo póster
            .padding(vertical = 24.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // --- 1. TEXTO SUPERIOR (Ej: JAPAN) ---
        Text(

            text = topTag.uppercase(),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = ArchivoFontFamily,
            letterSpacing = 4.sp // OJO: Esto le da el estilo separado del póster
        )

        // --- 2. TEXTO PRINCIPAL CON MÁSCARA DENTRO DE SU PROPIO BOX ---
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // CAPA 1: Borde exterior (Detrás)
            Text(
                text = title,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                style = TextStyle(
                    drawStyle = Stroke(width = borderWidth)
                ),
                color = borderColor,
            )

            // CAPA 2: Máscara recortada (Enfrente)
            Box(
                modifier = Modifier
                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                    .drawWithContent { drawContent() }
            ) {
                Text(
                    text = title,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                )

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer { blendMode = BlendMode.SrcIn }
                )
            }
        }

        // --- 3. TEXTO INFERIOR (Ej: LAND OF THE RISING SUN) ---
        Text(
            text = subTitle.uppercase(),
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily,
            letterSpacing = 4.sp
        )
    }
}

@Composable
fun TextImageMaskPosterAdjusted(
    modifier: Modifier = Modifier,
    topTag: String = "J A P A N",
    title: String = "TOKYO",
    subTitle: String = "LAND OF THE RISING SUN",
    imageRes: Int,
    borderColor: Color = Color.White,
    borderWidth: Float = 12f,
    titleFontSize: TextUnit = 110.sp,
    fontFamilyTitle: FontFamily = OswaldFontFamily,
    titleLetterSpacing: TextUnit,
    topTagLetterSpacing: TextUnit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Mantiene los elementos agrupados al centro
    ) {
        // 1. TEXTO SUPERIOR (Pegado con offset hacia abajo)


        // 2. TEXTO PRINCIPAL MÁSCARA (Letras más juntas)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = topTag.uppercase(),
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = RubicMonoOneFontFamily,
                letterSpacing = topTagLetterSpacing,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 9.dp)

            )
            // Capa 1: Borde
            Text(
                text = title,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamilyTitle,
                letterSpacing = titleLetterSpacing, // Kerning negativo para juntar letras
                style = TextStyle(drawStyle = Stroke(width = borderWidth)),


                color = borderColor,


            )

            // Capa 2: Recorte
            Box(
                modifier = Modifier
                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                    .drawWithContent { drawContent() }
            ) {
                Text(
                    text = title,
                    fontSize = titleFontSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamilyTitle,
                    letterSpacing = titleLetterSpacing, // Mismo letterSpacing para encajar exacto
                    color = Color.White,
                )

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer { blendMode = BlendMode.SrcIn }
                )
            }
        }

        // 3. TEXTO INFERIOR
        Text(
            text = subTitle.uppercase(),
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamilyTitle,
            letterSpacing = 3.sp,
            modifier = Modifier.offset(y = (-8).dp) // Lo acerca un poco a la base de TOKYO
        )
    }
}

@Preview
@Composable
fun TextImageMaskComposePreview() {
    TextImageMaskCompose(
        title = "Tokyo",
        fontSize = 130.sp,
        imageRes = R.drawable.img_3,
        fontFamily = AntonScFontFamily,
        modifier = Modifier
            .height(320.dp)
            .fillMaxWidth()
    )
}
@Preview
@Composable
fun TextImageMaskPosterPreview() {
    TextImageMaskPoster(
        topTag = "JAPAN",
        title = "Tokyo",
        subTitle = "LAND OF THE RISING SUN",
        fontFamily = AntonScFontFamily,
        imageRes = R.drawable.img_3,
        fontSize = 130.sp,
        modifier = Modifier
            .height(320.dp)
            .fillMaxWidth()
    )
}
@Preview
@Composable
fun TextImageMaskPosterAdjustedPreview() {
    TextImageMaskPosterAdjusted(
        topTag = "JAPAN",
        topTagLetterSpacing = 10.sp,
        title = "TOKYO",
        titleLetterSpacing = (9.5).sp,
        titleFontSize = 130.sp,
        subTitle = "LAND OF THE RISING SUN",
        imageRes = R.drawable.img_3,
        fontFamilyTitle = AntonScFontFamily,
        modifier = Modifier
            .height(320.dp)
            .fillMaxWidth()
    )
}
