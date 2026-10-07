package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogergcc.sampleprojectotter2026.R
// Forma alternativa super limpia usando la máscara del Drawable nativo

//para Gráficos estáticos, Pósteres o Ilustraciones tipo collage
//
//donde solo te interese enmascarar píxeles sin necesidad de interacciones o sombras.

@Composable
fun CardMaskFromDrawable(
    imageRes: Int,
    maskRes: Int,
    modifier: Modifier = Modifier
) {
    val maskPainter = painterResource(id = maskRes)

    Box(
        modifier = modifier
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                onDrawWithContent {
                    with(maskPainter) { draw(size) } // Máscara
                    drawContent() // Imagen
                }
            }
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { blendMode = BlendMode.SrcIn }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CardMaskFromDrawablePreview() {
    CardMaskFromDrawable(
        imageRes = R.drawable.img_2,
        maskRes = R.drawable.mask_cube_2_padding,
        modifier = Modifier.size(300.dp)
    )
}