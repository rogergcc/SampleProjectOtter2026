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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.theme.AntonScFontFamily

sealed class CardGalleryItem {
    data class StaticMask(
        val imageRes: Int,
        val maskRes: Int,
    ) : CardGalleryItem()

    data class DynamicMask(
        val imageUrl: String,
        val maskType: Int,
    ) : CardGalleryItem()

    data class RedditPost(
        val title: String,
        val body: String,
        val author: String,
        val subreddit: String,
    ) : CardGalleryItem()

    data class OrganicBird(val title: String) : CardGalleryItem()
    data class Card3d(val badgeTitle: String, val badDescription: String) : CardGalleryItem()

    data class CardGame3d(val gameTitle: String, val gameDescription: String) : CardGalleryItem()
    data class TextImageMaskCompose(
        val title: String,
        val fontSize: androidx.compose.ui.unit.TextUnit,
        val imageRes: Int,
        val fontFamily: androidx.compose.ui.text.font.FontFamily,
        val modifier: Modifier = Modifier,
    ) : CardGalleryItem()

    data class TextImageMaskPosterAdjusted(
        val topTag: String,
        val topTagLetterSpacing: androidx.compose.ui.unit.TextUnit,
        val title: String,
        val titleLetterSpacing: androidx.compose.ui.unit.TextUnit,
        val titleFontSize: androidx.compose.ui.unit.TextUnit,
        val subTitle: String,
        val imageRes: Int,
        val fontFamilyTitle: androidx.compose.ui.text.font.FontFamily,
        val modifier: Modifier = Modifier,
    ) : CardGalleryItem()

    data class MechaCardStyle(
        val title: String,
        val subtitle: String,
        val category: String,
        val footerText: String,
        val japaneseTag: String,
        val primaryColor: Color,
        val accentColor: Color,
        val imageRes: Int,
    ) : CardGalleryItem()

    // Agrega las variantes necesarias
}

@Composable
fun CollectionHistoryCard(
    modifier: Modifier = Modifier,
) {
    // 1. Datos desacoplados (DRY)
    val galleryItems = remember {
        listOf(

            CardGalleryItem.OrganicBird("Birds Panel"),

            CardGalleryItem.StaticMask(R.drawable.img_2, R.drawable.mask_cube_2_padding),
            CardGalleryItem.StaticMask(R.drawable.img_2, R.drawable.patter_circle_hoja),
            CardGalleryItem.StaticMask(R.drawable.img_1, R.drawable.vector_mask_pattern),
            CardGalleryItem.DynamicMask(
                imageUrl = "https://i.pinimg.com/1200x/5f/6e/3f/5f6e3fe28c43c7002edae5eda2ff0ceb.jpg",
                maskType = 3
            ),
            CardGalleryItem.DynamicMask(
                imageUrl = "https://i.pinimg.com/1200x/5f/6e/3f/5f6e3fe28c43c7002edae5eda2ff0ceb.jpg",
                maskType = 1
            ),
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
            CardGalleryItem.CardGame3d(
                gameTitle = "Space Invaders 3D",
                gameDescription = "Tu puntuación más alta: 12,345 puntos. ¡Sigue así!"
            ),

            CardGalleryItem.MechaCardStyle(
                title = "RX-93",
                subtitle = "GUNDAM",
                category = "E.F.S.F",
                footerText = "EARTH - FEDERATION - SPACE - FORCE",
                japaneseTag = "ボクシー",
                primaryColor = Color(0xFF2B6CB0),
                accentColor = Color(0xFFE53E3E),
                imageRes = R.drawable.gundam_iloveimg_remove
            ),
            CardGalleryItem.TextImageMaskCompose(
                title = "Tokyo",
                fontSize = 130.sp,
                imageRes = R.drawable.img_3,
                fontFamily = AntonScFontFamily,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ),
            CardGalleryItem.TextImageMaskPosterAdjusted(
                topTag = "JAPAN",
                topTagLetterSpacing = 10.sp,
                title = "TOKYO",
                titleLetterSpacing = (10.5).sp,
                titleFontSize = 130.sp,
                subTitle = "LAND OF THE RISING SUN",
                imageRes = R.drawable.img_3,
                fontFamilyTitle = AntonScFontFamily,
                modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth()
            ),

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
//            contentPadding = PaddingValues(horizontal = 32.dp), // Muestra los bordes de la tarjeta siguiente
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

                    is CardGalleryItem.CardGame3d -> Reddit3DRecapCard(
                        title = item.gameTitle,
                        subtitle = item.gameDescription
                    )

                    is CardGalleryItem.MechaCardStyle -> TacticalMechaCard(
                        config = MechaCardStyle(
                            title = item.title,
                            subtitle = item.subtitle,
                            category = item.category,
                            footerText = item.footerText,
                            japaneseTag = item.japaneseTag,
                            primaryColor = item.primaryColor,
                            accentColor = item.accentColor,
                            imageRes = item.imageRes
                        )
                    )

                    is CardGalleryItem.TextImageMaskCompose -> TextImageMaskCompose(
                        title = item.title,
                        fontSize = item.fontSize,
                        imageRes = item.imageRes,
                        fontFamily = item.fontFamily,
                        modifier = item.modifier
                    )

                    is CardGalleryItem.TextImageMaskPosterAdjusted -> TextImageMaskPosterAdjusted(
                        topTag = item.topTag,
                        topTagLetterSpacing = item.topTagLetterSpacing,
                        title = item.title,
                        titleLetterSpacing = item.titleLetterSpacing,
                        titleFontSize = item.titleFontSize,
                        subTitle = item.subTitle,
                        imageRes = item.imageRes,
                        fontFamilyTitle = item.fontFamilyTitle,
                        modifier = item.modifier,
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
    modifier: Modifier = Modifier,
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
