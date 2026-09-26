package com.rogergcc.sampleprojectotter2026.ui.composables.card

import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.theme.AppDimens
import com.rogergcc.sampleprojectotter2026.ui.theme.OswaldFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.SampleProjectOtter2026Theme
import com.rogergcc.sampleprojectotter2026.ui.tickets.CommemorativeTicketCard
import com.rogergcc.sampleprojectotter2026.ui.tickets.blackEyedPeasTicketConfig
import com.rogergcc.sampleprojectotter2026.ui.tickets.rememberTicketSensors

//@Composable
//fun StretchedPosterText(
//    text: String,
//    fontFamily: FontFamily,
//    modifier: Modifier = Modifier,
//    fillColor: Color? = null,
//    strokeColor: Color? = null,
//    strokeWidthDp: Dp = 1.dp,
//) {
//    val resolver = LocalFontFamilyResolver.current
//    val typefaceResult = resolver.resolve(
//        fontFamily = fontFamily,
//        fontWeight = FontWeight.Black,
//        fontStyle = FontStyle.Normal
//    )
//    val nativeTypeface = typefaceResult.value as android.graphics.Typeface
//    val strokeWidthPx = with(LocalDensity.current) { strokeWidthDp.toPx() }
//
//    Canvas(modifier = modifier.fillMaxWidth()) {
//        val basePaint = Paint().apply {
//            this.typeface = nativeTypeface
//            this.isAntiAlias = true
//            this.textSize = 100f
//        }
//
//        val textPath = Path()
//        basePaint.getTextPath(text, 0, text.length, 0f, 0f, textPath)
//
//        val pathBounds = RectF()
//        textPath.computeBounds(pathBounds, true)
//
//        val textWidth = pathBounds.width()
//        val textHeight = pathBounds.height()
//
//        if (textWidth > 0f && textHeight > 0f) {
//            val scaleX = size.width / textWidth
//            val scaleY = size.height / textHeight
//
//            val matrix = android.graphics.Matrix().apply {
//                postTranslate(-pathBounds.left, -pathBounds.top)
//                postScale(scaleX, scaleY)
//            }
//            textPath.transform(matrix)
//
//            val canvas = drawContext.canvas.nativeCanvas
//
//            fillColor?.let { color ->
//                val fillPaint = Paint().apply {
//                    this.color = color.toArgb()
//                    this.isAntiAlias = true
//                    this.style = Paint.Style.FILL
//                }
//                canvas.drawPath(textPath, fillPaint)
//            }
//
//            strokeColor?.let { color ->
//                val strokePaint = Paint().apply {
//                    this.color = color.toArgb()
//                    this.isAntiAlias = true
//                    this.style = Paint.Style.STROKE
//                    this.strokeWidth = strokeWidthPx
//                    this.strokeJoin = Paint.Join.ROUND
//                    this.strokeCap = Paint.Cap.ROUND
//                }
//                canvas.drawPath(textPath, strokePaint)
//            }
//        }
//    }
//}
//
///**
// * Bloque de tipografía unificado y parametrizado.
// */
//@Composable
//private fun PosterTypographyBlock(
//    modifier: Modifier = Modifier,
//    topText: String,
//    bottomText: String,
//    fontFamily: FontFamily,
//    topWeight: Float,
//    bottomWeight: Float,
//    lineSpacingDp: Dp,
//    fillColor: Color? = null,
//    strokeColor: Color? = null,
//    strokeWidthDp: Dp = 1.dp,
//) {
//    Column(
//        modifier = modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        StretchedPosterText(
//            text = topText,
//            fontFamily = fontFamily,
//            fillColor = fillColor,
//            strokeColor = strokeColor,
//            strokeWidthDp = strokeWidthDp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(topWeight)
//        )
//
//        if (lineSpacingDp > 0.dp) {
//            Spacer(modifier = Modifier.height(lineSpacingDp))
//        }
//
//        StretchedPosterText(
//            text = bottomText,
//            fontFamily = fontFamily,
//            fillColor = fillColor,
//            strokeColor = strokeColor,
//            strokeWidthDp = strokeWidthDp,
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(bottomWeight)
//        )
//    }
//}
//
//@Composable
//fun PosterFilmMaskTextImage(
//    imageRes: Int,
//    fontFamilyTitle: FontFamily,
//    modifier: Modifier = Modifier,
//    headerText: String = "FILM",
//    topText: String = "LEON",
//    bottomText: String = "PROFESSIONAL",
//    topWeight: Float = 1.0f,
//    bottomWeight: Float = 0.6f,
//    lineSpacingDp: Dp = 4.dp,
//    strokeWidthDp: Dp = 0.6.dp,
//    accentColor: Color = Color(0xFFA8020A),
//    imageScale: Float = 1.15f,
//    // Permite mover la imagen manualmente en Y para pegarla más abajo como el afiche original
//    offsetY: Dp = 10.dp,
//    colorMatrix: ColorMatrix? = null,
//) {
//    Card(
//        modifier = modifier
//            .width(280.dp)
//            .height(400.dp),
//        colors = CardDefaults.cardColors(containerColor = Color(0xFF101010)),
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(14.dp)
//                .border(2.dp, accentColor, RoundedCornerShape(4.dp))
//                .padding(8.dp)
//        ) {
//            Text(
//                text = headerText,
//                color = accentColor,
//                fontSize = 11.sp,
//                fontWeight = FontWeight.ExtraBold,
//                modifier = Modifier
//                    .align(Alignment.TopStart)
//                    .padding(top = 2.dp, start = 2.dp)
//            )
//
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 24.dp, bottom = 12.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                // CAPA 1 (FONDO)
//                PosterTypographyBlock(
//                    topText = topText,
//                    bottomText = bottomText,
//                    fontFamily = fontFamilyTitle,
//                    topWeight = topWeight,
//                    bottomWeight = bottomWeight,
//                    lineSpacingDp = lineSpacingDp,
//                    fillColor = accentColor
//                )
//
//                // CAPA 2 (MEDIO): Imagen con Crop e inclinación hacia el fondo
//                Image(
//                    painter = painterResource(id = imageRes),
//                    contentDescription = null,
//                    contentScale = ContentScale.FillWidth,
//                    alignment = Alignment.BottomCenter, // Se ancla abajo
//                    colorFilter = colorMatrix?.let { ColorFilter.colorMatrix(it) },
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .offset(y = offsetY) // Desplazamiento fino hacia abajo
//                        .graphicsLayer {
//                            scaleX = imageScale
//                            scaleY = imageScale
//                        }
//                )
//
//                // CAPA 3 (FRENTE)
//                PosterTypographyBlock(
//                    topText = topText,
//                    bottomText = bottomText,
//                    fontFamily = fontFamilyTitle,
//                    topWeight = topWeight,
//                    bottomWeight = bottomWeight,
//                    lineSpacingDp = lineSpacingDp,
//                    strokeColor = accentColor,
//                    strokeWidthDp = strokeWidthDp
//                )
//            }
//        }
//    }
//}

private data class PosterTextStyle(
    val fillColor: Color? = null,
    val strokeColor: Color? = null,
    val strokeWidthDp: Dp = 0.6.dp,
    val shadowColor: Color? = null,
    val shadowBlurDp: Dp = 0.dp,
    val shadowOffsetX: Dp = 0.dp,
    val shadowOffsetY: Dp = 0.dp
)
@Composable
private fun PosterTextLayer(
    textTop: String,
    textBottom: String,
    fontFamily: FontFamily,
    style: PosterTextStyle,
    topWeight: Float,
    bottomWeight: Float,
    lineSpacingDp: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StretchedPosterText(
            text = textTop,
            fontFamily = fontFamily,
            fillColor = style.fillColor,
            strokeColor = style.strokeColor,
            strokeWidthDp = style.strokeWidthDp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(topWeight)
        )

        if (lineSpacingDp > 0.dp) {
            Spacer(modifier = Modifier.height(lineSpacingDp))
        }

        StretchedPosterText(
            text = textBottom,
            fontFamily = fontFamily,
            fillColor = style.fillColor,
            strokeColor = style.strokeColor,
            strokeWidthDp = style.strokeWidthDp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(bottomWeight)
        )
    }
}

@Composable
fun PosterFilmMaskTextImage(
    imageRes: Int,
    fontFamilyTitle: FontFamily,
    modifier: Modifier = Modifier,
    headerText: String = "FILM",
    topText: String = "LEON",
    bottomText: String = "PROFESSIONAL",
    topWeight: Float = 1.0f,
    bottomWeight: Float = 0.6f,
    lineSpacingDp: Dp = 4.dp,
    strokeWidthDp: Dp = 0.6.dp,
    accentColor: Color = Color(0xFFA8020A),
    imageScale: Float = 1.15f,
    offsetY: Dp = 10.dp,
    colorMatrix: ColorMatrix? = null,
) {
    val baseTextStyle = PosterTextStyle(fillColor = accentColor)
    val frontTextStyle = PosterTextStyle(strokeColor = accentColor, strokeWidthDp = strokeWidthDp)

    Card(
        modifier = modifier
            .widthIn(max = AppDimens.dim300dp)
            .heightIn(max = AppDimens.dim400dp)
            .fillMaxWidth()
            .aspectRatio(0.75f)
        ,
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101010)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .border(2.dp, accentColor, RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text(
                text = headerText,
                color = accentColor,
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.TopStart)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, bottom = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                PosterTextLayer(
                    textTop = topText,
                    textBottom = bottomText,
                    fontFamily = fontFamilyTitle,
                    style = baseTextStyle,
                    topWeight = topWeight,
                    bottomWeight = bottomWeight,
                    lineSpacingDp = lineSpacingDp
                )

                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    colorFilter = colorMatrix?.let { ColorFilter.colorMatrix(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = offsetY)
                        .graphicsLayer {
                            scaleX = imageScale
                            scaleY = imageScale
                        }
                )

                PosterTextLayer(
                    textTop = topText,
                    textBottom = bottomText,
                    fontFamily = fontFamilyTitle,
                    style = frontTextStyle,
                    topWeight = topWeight,
                    bottomWeight = bottomWeight,
                    lineSpacingDp = lineSpacingDp
                )
            }
        }
    }
}
@Composable
private fun StretchedPosterText(
    text: String,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    fillColor: Color? = null,
    strokeColor: Color? = null,
    strokeWidthDp: Dp = 1.dp,
) {
    val resolver = LocalFontFamilyResolver.current
    val density = LocalDensity.current

    val nativeTypeface = remember(fontFamily) {
        val result = resolver.resolve(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Black,
            fontStyle = FontStyle.Normal
        )
        result.value as? android.graphics.Typeface
    } ?: return

    val strokeWidthPx = with(density) { strokeWidthDp.toPx() }

        Canvas(modifier = modifier.fillMaxWidth()) {
        val basePaint = Paint().apply {
            this.typeface = nativeTypeface
            this.isAntiAlias = true
            this.textSize = 100f
        }

        val textPath = Path()
        basePaint.getTextPath(text, 0, text.length, 0f, 0f, textPath)

        val pathBounds = RectF()
        textPath.computeBounds(pathBounds, true)

        val textWidth = pathBounds.width()
        val textHeight = pathBounds.height()

        if (textWidth > 0f && textHeight > 0f) {
            val scaleX = size.width / textWidth
            val scaleY = size.height / textHeight

            val matrix = android.graphics.Matrix().apply {
                postTranslate(-pathBounds.left, -pathBounds.top)
                postScale(scaleX, scaleY)
            }
            textPath.transform(matrix)

            val canvas = drawContext.canvas.nativeCanvas

            fillColor?.let { color ->
                val fillPaint = Paint().apply {
                    this.color = color.toArgb()
                    this.isAntiAlias = true
                    this.style = Paint.Style.FILL
                }
                canvas.drawPath(textPath, fillPaint)
            }

            strokeColor?.let { color ->
                val strokePaint = Paint().apply {
                    this.color = color.toArgb()
                    this.isAntiAlias = true
                    this.style = Paint.Style.STROKE
                    this.strokeWidth = strokeWidthPx
                    this.strokeJoin = Paint.Join.ROUND
                    this.strokeCap = Paint.Cap.ROUND
                }
                canvas.drawPath(textPath, strokePaint)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PosterFilmMaskTextImagePreview(){
    PosterFilmMaskTextImage(
        imageRes = R.drawable.leon_resize_2,
        fontFamilyTitle = OswaldFontFamily,
        headerText = "FILM",
        topText = "LEON",
        bottomText = "PROFESSIONAL",
        topWeight = 1.0f,
        bottomWeight = .6f,
        strokeWidthDp = .6.dp,
        lineSpacingDp = 4.dp,
        accentColor = Color(0xFFFFA726),
        imageScale = 1.0f,
        offsetY = 0.dp,
//        colorMatrix = BlueDuotoneMatrix,
    )
}

@Preview(showBackground = true)
@Composable
fun PosterFilmMaskTextImageCompletePreview(){

    SampleProjectOtter2026Theme {
        // Observamos el estado del giroscopio
        val sensorState by rememberTicketSensors()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212)),
            contentAlignment = Alignment.Center
        ) {
            PosterFilmMaskTextImage(
                imageRes = R.drawable.leon_resize_2,
                fontFamilyTitle = OswaldFontFamily,
                headerText = "FILM",
                topText = "LEON",
                bottomText = "PROFESSIONAL",
                topWeight = 1.0f,
                bottomWeight = .6f,
                strokeWidthDp = .6.dp,
                lineSpacingDp = 4.dp,
                accentColor = Color(0xFFFFA726),
                imageScale = 1.0f,
                offsetY = 0.dp,
            )
        }
    }


}