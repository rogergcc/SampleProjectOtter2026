package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogergcc.sampleprojectotter2026.R

/*
* Card Local Image Mask Clip
* Esta función crea una tarjeta con una imagen recortada usando una máscara SVG local.
* La máscara se aplica a la imagen para crear un efecto visual interesante.
* Se utiliza CompositingStrategy.Offscreen para aislar el renderizado y evitar borrar el fondo.
* La imagen se recorta usando BlendMode.SrcIn para que solo se vea la parte de la imagen que está dentro de la máscara.
* */

@Composable
fun CardImageMaskClip(
    modifier: Modifier = Modifier,
    imagePatterSvg: Int = R.drawable.patter_circle_hoja,
    imageResource: Int = R.drawable.img_1
) {
    // Carga de recursos en el ámbito @Composable (Lugar correcto)
//    val maskPainter = painterResource(id = R.drawable.vector_mask_pattern)

    val maskPainter = painterResource(id = imagePatterSvg)
    Box(
        modifier = modifier
            .size(300.dp)

            // Aísla el renderizado para no borrar el fondo
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            // Usamos la variable maskPainter ya inicializada arriba
            .drawWithCache {
                onDrawWithContent {
                    // 1. Dibujamos la MÁSCARA (Silueta)
                    with(maskPainter) {
                        draw(size)
                    }
                    // 2. Dibujamos el CONTENIDO que está adentro de la Box
                    drawContent()
                }
            }
    ) {
        // Imagen recortada que reacciona con la máscara usando BlendMode
        Image(
            painter = painterResource(imageResource),
            contentDescription = "Chica con lentes VR",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    // Le aplicamos BlendMode.SrcIn a la imagen
                    blendMode = BlendMode.SrcIn
                }
        )
    }

}


// --- VISTA PREVIA EN ANDROID STUDIO ---
@Preview(showBackground = true)
@Composable
fun PreviewCardImageMaskClip() {
    CardImageMaskClip(
        imageResource = R.drawable.img_2,
        imagePatterSvg = R.drawable.mask_cube_2_padding
    )
}
