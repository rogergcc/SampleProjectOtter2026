package com.rogergcc.sampleprojectotter2026.ui.composables.card


/**
 * Created on septiembre.
 * year 2026 .
 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rogergcc.sampleprojectotter2026.R

sealed class CardGalleryItem {
    data class StaticMask(
        val imageRes: Int,
        val maskRes: Int
    ) : CardGalleryItem()

    data class DynamicMask(
        val imageUrl: String,
        val maskType: Int
    ) : CardGalleryItem()

    data class RedditPost(
        val title: String,
        val body: String,
        val author: String,
        val subreddit: String
    ) : CardGalleryItem()

    data class OrganicBird(val title: String) : CardGalleryItem()
    data class Card3d(val badgeTitle: String, val badDescription:String) : CardGalleryItem()

    // Agrega las variantes necesarias
}

@Composable
fun CollectionHistoryCard(
    modifier: Modifier = Modifier
) {
    // 1. Datos desacoplados (DRY)
    val galleryItems = remember {
        listOf(
            CardGalleryItem.OrganicBird("Birds Panel"),
            CardGalleryItem.DynamicMask(
                imageUrl = "https://i.pinimg.com/1200x/5f/6e/3f/5f6e3fe28c43c7002edae5eda2ff0ceb.jpg",
                maskType = 3
            ),
            CardGalleryItem.StaticMask(R.drawable.img_2, R.drawable.mask_cube_2_padding),
            CardGalleryItem.StaticMask(R.drawable.img_2, R.drawable.patter_circle_hoja),
            CardGalleryItem.StaticMask(R.drawable.img_1, R.drawable.vector_mask_pattern),
            CardGalleryItem.RedditPost(
                title = "Jetpack Compose en 2026",
                body = "Optimizando galerías con KISS y DRY.",
                author = "kotlin_master",
                subreddit = "androiddev"
            ),
            CardGalleryItem.Card3d(
                badgeTitle = "Karma Champion",
                badDescription = "Obtuviste este sticker tras desbloquear los logros de comunidad de nivel Platino."
            ),
            CardGalleryItem.DynamicMask(
                imageUrl = "https://i.pinimg.com/1200x/5f/6e/3f/5f6e3fe28c43c7002edae5eda2ff0ceb.jpg",
                maskType = 1
            )

        )
    }

    val pagerState = rememberPagerState(pageCount = { galleryItems.size })

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        // 2. HorizontalPager con espaciado entre páginas para mejor UX
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 32.dp), // Muestra los bordes de la tarjeta siguiente
            pageSpacing = 16.dp
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // 3. Renderizado según el tipo de elemento (KISS)
                when (val item = galleryItems[page]) {
                    is CardGalleryItem.OrganicBird -> GouldianFinchPanel()
                    is CardGalleryItem.DynamicMask -> DynamicTicketStudioScreen(
                        imageUrl = item.imageUrl,
                        maskType = item.maskType
                    )
                    is CardGalleryItem.StaticMask -> CardImageMaskClip(
                        imageResource = item.imageRes,
                        imagePatterSvg = item.maskRes
                    )
                    is CardGalleryItem.RedditPost -> RedditPostCard(
                        subreddit = item.subreddit,
                        author = item.author,
                        timeAgo = "2h",
                        title = item.title,
                        bodyText = item.body,
                        voteCount = 412,
                        commentCount = 89,
                        onVoteUp = {}, onVoteDown = {}, onCommentClick = {}, onShareClick = {}
                    )
                    is CardGalleryItem.Card3d -> RedditSticker3DCard(
                        badgeName = item.badgeTitle,
                        badgeDescription = item.badDescription
                    )
                }
            }
        }

        // 4. Indicador de puntos (sin cambios visuales)
        PagerIndicator(
            pagerState = pagerState,
            itemCount = galleryItems.size,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}

@Composable
private fun PagerIndicator(
    pagerState: PagerState,
    itemCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(itemCount) { index ->
            val activo = pagerState.currentPage == index
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
@Preview(showBackground = true)
@Composable
fun CollectionHistoryCardPreview() {
    CollectionHistoryCard()
}
