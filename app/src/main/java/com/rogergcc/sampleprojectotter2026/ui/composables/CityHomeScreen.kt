package com.rogergcc.sampleprojectotter2026.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rogergcc.sampleprojectotter2026.R

/**
 * Created on febrero.
 * year 2026 .
 */
@Composable
fun CityHomeScreen(modifier: Modifier, preview: Boolean= false) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundImage(
            preview = preview
        )
        DarkOverlay()
        ContentMenu()
        CloseButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun BackgroundImage(
    modifier: Modifier = Modifier,
    preview: Boolean = false
) {
    if (preview) {
        Image(
            painter = painterResource(id = R.drawable.peru_unsplash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
    } else {
        AsyncImage(
            model = R.drawable.images_arequipa_catedral,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
    }
}


@Composable
fun BackgroundImage() {
    AsyncImage(
        model = R.drawable.peru_unsplash,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}


@Composable
fun DarkOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.6f),
                        Color.Transparent
                    )
                )

            )
    )
}

@Composable
fun ContentMenu() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "DESCUBRE",
            color = Color.White.copy(alpha = 0.8f),
            fontSize = 14.sp,
            letterSpacing = 2.sp
        )

        Text(
            text = "AREQUIPA",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        MenuItem("Sobre la ciudad")
        MenuItem("Mapa turístico")
        MenuItem("Rutas e itinerarios")
        MenuItem("Lugares históricos")
        MenuItem("Gastronomía")
    }
}


@Composable
fun MenuItem(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    )
}


@Composable
fun CloseButton(
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { /* cerrar */ },
        modifier = modifier
            .navigationBarsPadding()
            .size(48.dp)
            .background(
                color = Color(0xFF2B2B5E),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = Color.White
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CityHomeScreenPreview() {
    CityHomeScreen(
        modifier = Modifier.padding(16.dp),
        preview = true
    )
}
