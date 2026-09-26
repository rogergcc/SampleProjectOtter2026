package com.rogergcc.sampleprojectotter2026.ui.composables.card

import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.theme.AntonScFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.ArchivoFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.OswaldFontFamily

// Matriz de Duotono Neón (Cian/Azul Eléctrico) con sombras realzadas
val BrightBlueDuotoneMatrix = ColorMatrix(
    floatArrayOf(
        0.0f, 0.0f, 0.0f, 0.0f, 0f,       // Red
        0.0f, 0.5f, 0.0f, 0.0f, 40f,      // Green (un toque para brillo neón)
        0.0f, 0.0f, 1.0f, 0.0f, 100f,     // Blue (aumenta luminancia)
        0.0f, 0.0f, 0.0f, 1.0f, 0f        // Alpha
    )
)

@Composable
fun MartyStyleTicketCard(
    characterResId: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(320.dp)
            .height(500.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101010)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                // Marco delimitador en color naranja idéntico al póster original
                .border(2.dp, Color(0xFFFF5500), RoundedCornerShape(4.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            // CAPA 1: Encabezado superior tipo marcas
            Text(
                text = "TIMOTHÉE CHALAMET",
                color = Color(0xFFFF5500),
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(4.dp)
            )

            // CAPA 2: Bloque Tipográfico Gigante Estirado
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "MARTY",
                    color = Color(0xFFFF5500),
                    fontSize = 76.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = (-2).sp, // Comprime las letras
                    textAlign = TextAlign.Center,
                    modifier = Modifier.scale(scaleX = 1f, scaleY = 1.3f) // Estiramiento vertical
                )
                Text(
                    text = "SUPREME",
                    color = Color(0xFFFF5500),
                    fontSize = 58.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.SansSerif,
                    letterSpacing = (-1).sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .scale(scaleX = 1.1f, scaleY = 1.3f)
                )
            }

            // CAPA 3: Personaje Duotono con mayor luminancia
            Image(
                painter = painterResource(id = characterResId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.colorMatrix(BrightBlueDuotoneMatrix),
                modifier = Modifier
                    .fillMaxSize(0.9f)
                    .align(Alignment.Center)
            )
        }
    }
}

val BlueDuotoneMatrix = ColorMatrix(
    floatArrayOf(
        0f, 0f, 0f, 0f, 0f,
        0f, 0.4f, 0f, 0f, 40f,
        0f, 0f, 1f, 0f, 100f,
        0f, 0f, 0f, 1f, 0f
    )
)

@Composable
fun MartyClippedTextCard(
    characterResId: Int,
    text: String,
    modifier: Modifier = Modifier,
    colorMatrix: ColorMatrix? = null,
    borderColor: Color = Color(0xFFFF5500),
    textColor: Color = Color(0xFFFF5500)
) {
    Card(
        modifier = modifier
            .width(320.dp)
            .height(500.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101010)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .border(2.dp, borderColor, RoundedCornerShape(4.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        compositingStrategy = CompositingStrategy.Offscreen
                    },
                contentAlignment = Alignment.Center
            ) {
                // PASO 1: Usamos BasicText con AutoSize para el escalado perfecto
                BasicText(
                    text = text,
                    // Forzamos a que el sistema busque el tamaño idóneo entre 20sp y 100sp
                    autoSize = TextAutoSize.StepBased(
                        minFontSize = 40.sp,
                        maxFontSize = 100.sp,
                        stepSize = 1.sp
                    ),
                    // Mantenemos tus configuraciones tipográficas estilizadas
                    style = TextStyle(
                        color = textColor,
                        fontWeight = FontWeight.Black,
                        fontFamily = AntonScFontFamily,
                        textAlign = TextAlign.Center,
                        letterSpacing = 4.5.sp
                    ),
                    maxLines = 1,
                    softWrap = false, // Impide saltos de línea accidentales
                    modifier = Modifier
                        .fillMaxWidth() // Ocupa todo el ancho disponible del Box interno
                        .scale(scaleX = 1.05f, scaleY = 1.3f)
                    // Eliminamos el .scale() manual porque el sistema ya agranda la tipografía dinámicamente
                )

                // PASO 2: Imagen adaptada (Crop para rellenar los bordes)
                Image(
                    painter = painterResource(id = characterResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    colorFilter = colorMatrix?.let { ColorFilter.colorMatrix(it) },
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            blendMode = BlendMode.SrcIn
                        }
                )
            }
        }
    }
}


@Composable
fun MartyPosterCardCorrect(
    characterResId: Int,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(320.dp)
            .height(500.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101010)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .border(2.dp, Color(0xFFFF5500), RoundedCornerShape(4.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            // CAPA 1: Encabezado superior
            Text(
                text = "TIMOTHÉE CHALAMET",
                color = Color(0xFFFF5500),
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(4.dp)
            )

            // CAPA 2: LÓGICA DE INTERSECCIÓN (Offscreen Canvas)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        // Aísla esta mezcla para que no afecte el fondo negro
                        compositingStrategy = CompositingStrategy.Offscreen
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "MARTY",
                        color = Color(0xFFFF5500),
                        fontSize = 82.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = AntonScFontFamily,
                        textAlign = TextAlign.Center,
                        letterSpacing = 4.sp, // Aumenta el espaciado para expandir "MARTY" (5 letras)
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(scaleX = 1.05f, scaleY = 1.8f) // Estiramiento vertical
                    )

                    Text(
                        text = "SUPREME",
                        color = Color(0xFFFF5500),
                        fontSize = 58.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = AntonScFontFamily,
                        textAlign = TextAlign.Center,
                        letterSpacing = (-1.5).sp, // Comprime "SUPREME" (7 letras) para calzar los bordes
                        modifier = Modifier
                            .fillMaxWidth()
                            .scale(scaleX = 1.0f, scaleY = 1.3f)
                    )
                }

                // PASO B: Personaje en Duotono que SE MEZCLA (Multiply / SrcAtop) sobre el texto
                Image(
                    painter = painterResource(id = characterResId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.colorMatrix(BlueDuotoneMatrix),
                    modifier = Modifier
                        .fillMaxSize(0.9f)
                        .graphicsLayer {
                            // SrcAtop mantiene el color Naranja de fondo en las partes no tocadas,
                            // pero DIBUJA la imagen azul encima respetando únicamente el área del texto
                            blendMode = BlendMode.SrcAtop
                        }
                )
            }
        }
    }
}



/**
 * Dibuja texto reescalando la matriz del Canvas para estirarlo y forzarlo
 * a ocupar exactamente el 100% del ancho y alto del composable.
 */
@Composable
fun StretchedTextCanvas(
    text: String,
    color: Color,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier
) {
    // Convertimos el FontFamily de Compose a un Typeface nativo de Android
    val resolver = LocalFontFamilyResolver.current
    val typefaceResult = resolver.resolve(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Normal,

    )
    val nativeTypeface = typefaceResult.value as android.graphics.Typeface

    Canvas(modifier = modifier.fillMaxWidth()) {
        val paint = Paint().apply {
            this.color = color.toArgb()
            this.typeface = nativeTypeface
            this.isAntiAlias = true
            this.textSize = 100f // Tamaño base de medición

        }

        val textBounds = Rect()
        paint.getTextBounds(text, 0, text.length, textBounds)

        val textWidth = textBounds.width().toFloat()
        val textHeight = textBounds.height().toFloat()

        if (textWidth > 0f && textHeight > 0f) {
            val scaleX = size.width / textWidth
            val scaleY = size.height / textHeight

            drawContext.canvas.nativeCanvas.save()
            drawContext.canvas.nativeCanvas.scale(scaleX, scaleY)

            drawContext.canvas.nativeCanvas.drawText(
                text,
                -textBounds.left.toFloat(),
                -textBounds.top.toFloat(),
                paint
            )
            drawContext.canvas.nativeCanvas.restore()
        }
    }
}

@Composable
fun MartyPosterCardPerfect2(
    characterResId: Int,
    fontFamily: FontFamily, // Pasa aquí tu AntonScFontFamily
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(300.dp)
            .height(420.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF101010)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .border(2.dp, Color(0xFFFF5500), RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Text(
                text = "TIMOTHÉE CHALAMET",
                color = Color(0xFFFF5500),
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 2.dp, start = 2.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, bottom = 12.dp)
                    .graphicsLayer {
                        compositingStrategy = CompositingStrategy.Offscreen
                    },
                contentAlignment = Alignment.Center
            ) {
                // A) BLOQUE TIPOGRÁFICO AJUSTADO DE BORDE A BORDE
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StretchedTextCanvas(
                        text = "MARTY",
                        color = Color(0xFFFF5500),
                        fontFamily = fontFamily,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    StretchedTextCanvas(
                        text = "SUPREME",
                        color = Color(0xFFFF5500),
                        fontFamily = fontFamily,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.5f)
                            .padding(top = 4.dp)
                    )
                }

                // B) ILUSTRACIÓN CON SrcAtop
                androidx.compose.foundation.Image(
                    painter = painterResource(id = characterResId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.colorMatrix(BlueDuotoneMatrix),
                    modifier = Modifier
                        .fillMaxSize(0.95f)
                        .graphicsLayer {
                            blendMode = BlendMode.SrcAtop
                        }
                )
            }
        }
    }
}
///////////////////////////////




@Preview(showBackground = true, backgroundColor = 0xFF121212)
@Composable
fun MartyStyleTicketCardPreview() {
    MartyStyleTicketCard(
        modifier = Modifier,
        characterResId = R.drawable.gundam_2

    )
}

@Preview(showBackground = true)
@Composable
fun MartyClippedTextCardPreview() {
    MartyClippedTextCard(
        characterResId = R.drawable.img_3,
        text = "BIRD",
        borderColor = Color.Green,
        textColor = Color.Green,
    )
}

@Preview(showBackground = true)
@Composable
fun MartyPosterCardCorrectPreview() {
    MartyPosterCardCorrect(
        modifier = Modifier,
        characterResId = R.drawable.gundam_2
    )
}


@Preview(showBackground = true)
@Composable
fun MartyPosterCardPerfect2Preview(){
    MartyPosterCardPerfect2(
        modifier = Modifier,
        characterResId = R.drawable.gundam_2,
        fontFamily = ArchivoFontFamily,
    )
}


@Composable
fun MartyPosterCard3D(
    characterResId: Int,
    textLines: List<String>,
    fontFamily: FontFamily,               // 🌟 PARAMETRIZABLE: Cambia la tipografía dinámicamente
    modifier: Modifier = Modifier,
    containerColor: Color = Color(0xFF101010), // PARAMETRIZABLE: Color de fondo de la tarjeta
    borderColor: Color = Color(0xFFFF5500),    // PARAMETRIZABLE: Color del borde externo
    textColor: Color = Color(0xFFFF5500),      // PARAMETRIZABLE: Color base de iluminación
    shadowAlpha: Float = 0.6f                  // PARAMETRIZABLE: Intensidad de la sombra de separación
) {
    // 🌟 MATRIZ DINÁMICA (KISS & DRY):
    // Toma los canales del `textColor` y los inyecta como un desfase constante (+offset)
    // en la última columna. Esto ilumina la textura original con el tono elegido sin perder los detalles.
    val dynamicColorMatrix = ColorMatrix(
        floatArrayOf(
            1f, 0f, 0f, 0f, textColor.red * 255f,   // Desfase Rojo
            0f, 1f, 0f, 0f, textColor.green * 255f, // Desfase Verde
            0f, 0f, 1f, 0f, textColor.blue * 255f,  // Desfase Azul
            0f, 0f, 0f, 1f, 0f
        )
    )

    Card(
        modifier = modifier
            .width(320.dp)
            .height(500.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .border(2.dp, borderColor, RoundedCornerShape(4.dp))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {

            // DRY: Centralizamos la estructura del bloque de texto en una única lambda local
            val textBlockStructure = @Composable {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    textLines.forEach { line ->
                        BasicText(
                            text = line,
                            autoSize = TextAutoSize.StepBased(
                                minFontSize = 20.sp,
                                maxFontSize = 120.sp,
                                stepSize = 1.sp
                            ),
                            style = TextStyle(
                                color = textColor,
                                fontWeight = FontWeight.Black,
                                fontFamily = fontFamily, // Asignación dinámica de la fuente
                                textAlign = TextAlign.Center
                            ),
                            maxLines = 1,
                            softWrap = false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .scale(scaleX = 1.05f, scaleY = 1.4f)
                        )
                    }
                }
            }

            // =================================================================
            // CAPA 1 (FONDO TOTAL): El ave en su color real completo sin alterar
            // =================================================================
            Image(
                painter = painterResource(id = characterResId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize(0.9f)
            )

            // =================================================================
            // CAPA 2 (INTERMEDIO): Silueta oscura (CORREGIDA)
            // =================================================================
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        compositingStrategy = CompositingStrategy.Offscreen
                    },
                contentAlignment = Alignment.Center
            ) {
                textBlockStructure()

                // SOLUCIÓN: Agregamos .background() para que la mezclaSrcIn cala una sombra real
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            blendMode = BlendMode.SrcIn
                        }
                        .background(Color.Black.copy(alpha = shadowAlpha))
                )
            }

            // =================================================================
            // CAPA 3 (FRENTE): El texto con la textura original iluminada dinámicamente
            // =================================================================
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        compositingStrategy = CompositingStrategy.Offscreen
                    },
                contentAlignment = Alignment.Center
            ) {
                textBlockStructure()

                Image(
                    painter = painterResource(id = characterResId),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    // Aplicamos la matriz autogenerada en base a tu color dinámico
                    colorFilter = ColorFilter.colorMatrix(dynamicColorMatrix),
                    modifier = Modifier
                        .fillMaxSize(0.9f)
                        .graphicsLayer {
                            blendMode = BlendMode.SrcIn
                        }
                )
            }
        }
    }
}


// Opción 2: El diseño de póster 3D donde la banda se ve completa y se fusiona con las letras
@Preview(showBackground = true)
@Composable
fun PosterVerdePreview() {
    MartyPosterCard3D(
        characterResId = R.drawable.ave_transparent,
        textLines = listOf("BI", "RD"),
        fontFamily = ArchivoFontFamily,          // Fuente dinámica
        containerColor = Color(0xFF42A5F5),      // Fondo melocotón
        borderColor = Color(0xFF00E5FF),         // Borde Cian Neón
        textColor = Color(0xFFFFCA28),                 // Letras iluminadas en verde
        shadowAlpha = 0.4f                       // Sombra sutil
    )
}


@Composable
fun ImageMaskedTextPoster(
    modifier: Modifier = Modifier,
    foregroundImageRes: Int,             // ave_transparent.png
    backgroundImageRes: Int,             // ave_fondo.jpg
    textLines: List<String>,
    fontFamily: FontFamily,
    imageScale: Float = 0.95f,
    textScaleX: Float = 1.0f,            // Escala horizontal del texto (1.0f = normal)
    textScaleY: Float = 1.3f,            // Escala vertical del texto (> 1.0f para estirar)
    textOverlayAlpha: Int = 80,          // Opacidad (0 a 255)
    textOverlayColor: Color = Color.Black
) {
    val context = LocalContext.current
    val resolver = LocalFontFamilyResolver.current

    val typefaceResult = resolver.resolve(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Normal
    )
    val nativeTypeface = typefaceResult.value as android.graphics.Typeface

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        val options = BitmapFactory.Options().apply { inScaled = false }
        val fgBitmap = BitmapFactory.decodeResource(context.resources, foregroundImageRes, options) ?: return@Canvas
        val bgBitmap = BitmapFactory.decodeResource(context.resources, backgroundImageRes, options) ?: fgBitmap

        val scale = maxOf(width / fgBitmap.width.toFloat(), height / fgBitmap.height.toFloat()) * imageScale
        val scaledW = fgBitmap.width * scale
        val scaledH = fgBitmap.height * scale
        val left = (width - scaledW) / 2f
        val top = (height - scaledH) / 2f

        val srcFgRect = Rect(0, 0, fgBitmap.width, fgBitmap.height)
        val srcBgRect = Rect(0, 0, bgBitmap.width, bgBitmap.height)
        val dstRect = RectF(left, top, left + scaledW, top + scaledH)

        drawIntoCanvas { canvas ->
            val nativeCanvas = canvas.nativeCanvas
            val paint = android.graphics.Paint().apply { isAntiAlias = true }

            // 1. DIBUJAR AVE TRANSPARENTE AL FONDO
            nativeCanvas.drawBitmap(fgBitmap, srcFgRect, dstRect, paint)

            // 2. CONSTRUIR Y ESCALAR TRAZADO DEL TEXTO
            val textPaint = android.graphics.Paint().apply {
                this.typeface = nativeTypeface
                this.isAntiAlias = true
                this.textSize = height / (textLines.size * 1.05f)
                this.textAlign = android.graphics.Paint.Align.CENTER
            }

            // Compensamos el interlineado según la escala vertical aplicada
            val lineSpacing = textPaint.textSize * 0.85f * textScaleY
            val totalTextHeight = textLines.size * lineSpacing
            val startY = (height - totalTextHeight) / 2f + (textPaint.textSize * 0.75f * textScaleY)

            val textPath = android.graphics.Path()
            val scaleMatrix = Matrix()

            textLines.forEachIndexed { index, line ->
                val x = width / 2f
                val y = startY + (index * lineSpacing)
                val linePath = android.graphics.Path()
                textPaint.getTextPath(line, 0, line.length, x, y, linePath)

                // Aplicar transformación escalar tomando como pivote el centro de cada línea
                scaleMatrix.reset()
                scaleMatrix.postScale(textScaleX, textScaleY, x, y)
                linePath.transform(scaleMatrix)

                textPath.addPath(linePath)
            }

            // 3. RECORTE DE TEXTO CON FILTRO DE INTENSIDAD
            nativeCanvas.save()
            nativeCanvas.clipPath(textPath)

            // Dibujar la foto completa alineada
            nativeCanvas.drawBitmap(bgBitmap, srcBgRect, dstRect, paint)

            // Tinte dinámico dentro del texto
            if (textOverlayAlpha > 0) {
                val overlayPaint = android.graphics.Paint().apply {
                    color = android.graphics.Color.argb(
                        textOverlayAlpha,
                        (textOverlayColor.red * 255).toInt(),
                        (textOverlayColor.green * 255).toInt(),
                        (textOverlayColor.blue * 255).toInt()
                    )
                    isAntiAlias = true
                }
                nativeCanvas.drawPath(textPath, overlayPaint)
            }

            nativeCanvas.restore()
        }
    }
}

@Composable
fun RethinkPosterCard(
    modifier: Modifier = Modifier,
    foregroundImageRes: Int = R.drawable.ave_transparent,
    backgroundImageRes: Int = R.drawable.ave_fondo,
    textLines: List<String> = listOf("RE", "THI", "NK"),
    fontFamily: FontFamily = OswaldFontFamily,
    backgroundColor: Color = Color(0xFFF5F5F5),
    textOverlayAlpha: Int = 85, // Controla la intensidad de la sombra en el texto (0 a 255)
    textScaleX: Float,
    textScaleY: Float,

) {
    Card(
        modifier = modifier.size(width = 320.dp, height = 440.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        ImageMaskedTextPoster(
            foregroundImageRes = foregroundImageRes,
            backgroundImageRes = backgroundImageRes,
            textLines = textLines,
            fontFamily = fontFamily,
            textScaleX = textScaleX,  // Ancho normal
            textScaleY = textScaleY, // Estirado vertical al 135%
            textOverlayAlpha = textOverlayAlpha
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RethinkPosterCardPreview() {
    RethinkPosterCard(
        foregroundImageRes = R.drawable.ave_transparent,
        backgroundImageRes = R.drawable.ave_fondo,
        textLines = listOf("RE", "THI", "NK"),
        textScaleX = .9f,  // Ancho normal
        textScaleY = 1.19f,
        fontFamily = ArchivoFontFamily,
        backgroundColor = Color(0xFFF5F5F5),
        textOverlayAlpha = 10 // Prueba subiendo a 100 o 120 si quieres que resalten aún más
    )
}