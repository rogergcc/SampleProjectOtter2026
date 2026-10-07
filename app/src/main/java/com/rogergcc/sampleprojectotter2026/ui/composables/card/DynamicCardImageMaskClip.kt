package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.rogergcc.sampleprojectotter2026.R

@Composable
fun DynamicCardImageMaskClip(
    imageUrl: String,    // Recibe el enlace de DeviantArt, Imgur, etc.
    maskType: Int,       // 1 = Estrella, 2 = Ticket, 3 = Corazón
    modifier: Modifier = Modifier
) {
    // 1. Cargamos la imagen desde internet usando Coil
    val internetImagePainter = rememberAsyncImagePainter(model = imageUrl)

    // 2. Elegimos la máscara matemática o vectorial según el parámetro de la URL
    val selectedMaskPainter = when (maskType) {
        2 -> painterResource(id = R.drawable.vector_mask_pattern)
        3 -> painterResource(id = R.drawable.mask_cube_2_padding)
        else -> painterResource(id = R.drawable.patter_circle_hoja) // Tu máscara original
    }

    Box(
        modifier = modifier
            .size(300.dp)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                onDrawWithContent {
                    // Dibujamos la máscara dinámica elegida
                    with(selectedMaskPainter) {
                        draw(size)
                    }
                    drawContent()
                }
            }
    ) {
        Image(
            painter = internetImagePainter,
            contentDescription = "Imagen dinámica cargada por DeepLink",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    blendMode = BlendMode.SrcIn
                }
        )
    }
}

@Composable
fun DynamicTicketStudioScreen(
    imageUrl: String,
    maskType: Int
) {
// 1. ESTADOS DE ROTACIÓN: Guardan cuántos grados se debe inclinar la tarjeta
    var rotateX by remember { mutableStateOf(0f) }
    var rotateY by remember { mutableStateOf(0f) }

    // 2. ANIMACIÓN DE RETORNO: Cuando el usuario suelta la pantalla,
    // los valores vuelven a 0f de forma suave y amortiguada (Inercia física)
    val animatedRotateX by animateFloatAsState(
        targetValue = rotateX,
        animationSpec = tween(durationMillis = 200),
        label = "RotationX"
    )
    val animatedRotateY by animateFloatAsState(
        targetValue = rotateY,
        animationSpec = tween(durationMillis = 200),
        label = "RotationY"
    )

    // Aquí puedes meter los estados de rotación 3D (rotateX, rotateY) que creamos antes
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Tu tarjeta contenedora con físicas de arrastre
        Card(
            modifier = Modifier
                .size(300.dp)
                .graphicsLayer {
                    rotationX = animatedRotateX
                    rotationY = animatedRotateY
                    cameraDistance = 16f // Le da la profundidad realista al espacio 3D
                }
                // Detectamos los arrastres del dedo en la pantalla
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()

                            // Multiplicamos por 0.15f para que el movimiento no sea demasiado brusco
                            // CoerceIn limita la inclinación máxima a 25 grados para no deformar el diseño
                            rotateX = (rotateX - dragAmount.y * 0.15f).coerceIn(-25f, 25f)
                            rotateY = (rotateY + dragAmount.x * 0.15f).coerceIn(-25f, 25f)
                        },
                        onDragEnd = {
                            // Al soltar el dedo, regresamos la tarjeta a su estado plano original
                            rotateX = 0f
                            rotateY = 0f
                        }
                    )
                },
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent), // El color lo maneja tu máscara y Shimmer
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)

        ) {
            // 4. TU COMPONENTE DINÁMICO (Hijo)
            // Al estar dentro de la Card con graphicsLayer, TODO lo que dibuje tu componente
            // (La imagen de internet, el Shimmer de carga o el icono de error)
            // se moverá e inclinará en 3D de forma unificada.
            DynamicCardImageMaskClip(
                imageUrl = imageUrl,
                maskType = maskType,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DynamicCardImageMaskClipPreview() {

    DynamicCardImageMaskClip(
        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5oLjoSBmduke0s0JMFMig52hQGcSugSINhSUjfIHkjIGDkVJRbAGqd3A&s=10", // Reemplaza con un enlace válido
        maskType = 3 ,
        modifier = Modifier.fillMaxSize()
    )
}