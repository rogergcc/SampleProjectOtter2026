package com.rogergcc.sampleprojectotter2026.ui.helpers

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun VectorTextureOverlay(
    @DrawableRes resId: Int,
    primaryTint: Color,
    secondaryTint: Color? = null,
    roll: Float,
    pitch: Float,
    scale: Float = 1.0f,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Capa 1: Vector principal tintado
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                color = primaryTint,
                blendMode = BlendMode.SrcIn
            ),
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationX = roll * 1.2f
                    translationY = pitch * 1.2f
                    scaleX = scale
                    scaleY = scale
                }
        )

        // Capa 2 (Opcional): Si defines un segundo color, rinde el mismo SVG desfasado (Efecto 3D)
        secondaryTint?.let { tintColor ->
            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    color = tintColor,
                    blendMode = BlendMode.SrcIn
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        translationX = -roll * 1.8f
                        translationY = -pitch * 1.8f
                        scaleX = scale * 1.12f
                        scaleY = scale * 1.12f
                    }
            )
        }
    }
}