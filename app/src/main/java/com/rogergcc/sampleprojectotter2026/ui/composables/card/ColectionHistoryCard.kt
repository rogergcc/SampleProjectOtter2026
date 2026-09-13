package com.rogergcc.sampleprojectotter2026.ui.composables.card


/**
 * Created on septiembre.
 * year 2026 .
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.composables.DynamicTicketStudioScreen
import com.rogergcc.sampleprojectotter2026.ui.composables.RedditPostCard

@Composable
fun CollectionHistoryCard() {
    // 1. Creamos la lista de tus composables reales usando lambdas (@Composable () -> Unit)
    val misDisenos: List<@Composable () -> Unit> = listOf(
        {
            GouldianFinchPanel(
                modifier = Modifier
                    .padding(16.dp)
            )
        },
        { OrganicBirdMaskCard() },
        {
            DynamicTicketStudioScreen(
                imageUrl = "https://i.pinimg.com/1200x/5f/6e/3f/5f6e3fe28c43c7002edae5eda2ff0ceb.jpg",
                maskType = 3 // Cambia a 1, 2 o 3 para probar diferentes máscaras
            )

//            DynamicCardImageMaskClip(
//                imageUrl = "https://i.pinimg.com/1200x/5f/6e/3f/5f6e3fe28c43c7002edae5eda2ff0ceb.jpg",
//                maskType = 1,
//            )
        },
        {
            CardImageMaskClip(
                imageResource = R.drawable.img_2,
                imagePatterSvg = R.drawable.mask_cube_2_padding
            )
        },
        {
            CardImageMaskClip(
                imageResource = R.drawable.img_2,
                imagePatterSvg = R.drawable.patter_circle_hoja
            )
        },
        {
            CardImageMaskClip(
                imageResource = R.drawable.img_2,
                imagePatterSvg = R.drawable.vector_mask_pattern
            )
        },
        {
            CardImageMaskClip(
                imageResource = R.drawable.img_1,
                imagePatterSvg = R.drawable.vector_mask_pattern
            )
        },
        {
            CardImageMaskClip(
                imageResource = R.drawable.img_1,
                imagePatterSvg = R.drawable.patter_circle_hoja
            )
        },
        {
            CardImageMaskClip(
                imageResource = R.drawable.img_1,
                imagePatterSvg = R.drawable.mask_cube_2_padding
            )
        },
        { Reddit3DRecapCard(title = "Explorador de Mentes", subtitle = "Pasaste el 45%...") },
        {
            RedditRecapCard(
                username = "AndroidDevSnoo",
                abilityName = "Infinite Compiler",
                abilityDescription = "+50% de velocidad al resolver bugs de recomposición en hilos secundarios.",
                rarityTier = "EPIC",
                topSubreddits = listOf("androiddev", "JetpackCompose", "kotlin")
            )
        },
        {
            MaterialTheme {
                RedditPostCard(
                    subreddit = "androiddev",
                    author = "kotlin_master",
                    timeAgo = "2 h",
                    title = "Jetpack Compose en 2024: ¿Por qué es el estándar absoluto de la industria?",
                    bodyText = "Llevo usando Compose desde la versión beta y las optimizaciones de rendimiento actuales son increíbles. El renderizado de listas complejas y la facilidad de animar estados con modificadores nativos superan por mucho al antiguo ecosistema basado en vistas XML.",
                    voteCount = 412,
                    commentCount = 89,
                    onVoteUp = {},
                    onVoteDown = {},
                    onCommentClick = {},
                    onShareClick = {}
                )
            }
        },
        {
            RedditSticker3DCard(
                badgeName = "Karma Champion",
                badgeDescription = "Obtuviste este sticker tras desbloquear los logros de comunidad de nivel Platino."
            )
        }

    )

    val pagerState = rememberPagerState(pageCount = { misDisenos.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)) // Fondo oscuro de pasarela
    ) {
        // 2. El Pager ocupa TODA la pantalla
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Ejecutamos el composable que toca en esta página
                misDisenos[page]()
            }
        }

        // 3. Indicador de puntitos flotando abajo
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(misDisenos.size) { index ->
                val activo = pagerState.currentPage == index
                // Cerrar el paréntesis directamente, sin llaves al final
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(if (activo) 9.dp else 6.dp)
                        .background(
                            color = if (activo) Color.Cyan else Color.DarkGray,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectionHistoryCardPreview() {
    CollectionHistoryCard()
}
