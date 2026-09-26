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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.theme.AntonScFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.ArchivoFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.OswaldFontFamily

sealed class CardGalleryItem {
    data class StaticMask(
        val imageRes: Int,
        val maskRes: Int,
    ) : CardGalleryItem()

    data class CardMaskFromDrawable(
        val imageRes: Int,
        val maskRes: Int,
        val modifier: Modifier = Modifier,
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
    data class OrganicBirdMaskCard(val imageRes: Int) : CardGalleryItem()

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
    data class CharacterPosterCard(
        val data: AnimeCharacterCardData,
        val modifier: Modifier = Modifier,
        val cardHeight: Dp = 480.dp
    ) : CardGalleryItem()

    class MartyPosterCardPerfect(
        val modifier: Modifier = Modifier,
        val imageRes: Int,
        val fontFamilyTitle: FontFamily,
        val topWeight: Float,
        val bottomWeight: Float,
        val strokeWidthDp: Dp,
        val headerText: String,
        val topText: String,
        val bottomText: String,
        val lineSpacingDp: Dp,
        val accentColor: Color,
        val imageScale: Float,
        val offsetY: Dp,
        ): CardGalleryItem()

    class MartyPosterCard3D(
        val characterResId: Int,
        val textLines: List<String>,
        val fontFamily : FontFamily,
        val containerColor: Color,
        val borderColor: Color,
        val textColor: Color,
    ) : CardGalleryItem()

    class ImageMaskedTextPoster(
        val foregroundImageRes: Int,
        val backgroundImageRes: Int,
        val textLines: List<String>,
        val fontFamily: FontFamily,
        val textScaleX: Float,
        val textScaleY: Float,
        val backgroundColor: Color,
        val textOverlayAlpha: Int
    ) : CardGalleryItem()

    class EditorialFilmPosterCard(
        val backgroundImageRes: Int,
        val foregroundImageRes: Int,
        val cardIntensity: Float,
        val title: String,
        val titleFontSize: TextUnit,
        val titleFontFamily: FontFamily,
        val scaleXY: Pair<Float, Float>,
        val castLeft: String,
        val castRight: String,
        val footer: String,
        val year: String,
        val modifier: Modifier,
        val titleStrokeWith: Float,
    ) : CardGalleryItem()
}

@Composable
fun CollectionHistoryCard(
    modifier: Modifier = Modifier,
) {
    // 1. Datos desacoplados (DRY)
    val galleryItems = remember {
        listOf(
            CardGalleryItem.EditorialFilmPosterCard(
                backgroundImageRes = R.drawable.leon_complete,
                foregroundImageRes = R.drawable.leon_sameposition,
                cardIntensity = 0.5f,
                title = "LEON",
                titleFontSize= 130.sp,
                titleFontFamily = OswaldFontFamily,
                titleStrokeWith = 2.0f,
                scaleXY = 1.11f to 2f,
                castLeft = "NATALIE PORTMAN",
                castRight = "GARY OLDMAN",
                footer = "English-Language French Action Crime Thriller film written and directed by Luc Besson",
                year = "1994",
                modifier = Modifier.width(300.dp),
            ),
            CardGalleryItem.MartyPosterCardPerfect(
                imageRes = R.drawable.leon_sameposition,
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
                modifier = Modifier.width(300.dp)
            ),
            CardGalleryItem.ImageMaskedTextPoster(
                foregroundImageRes = R.drawable.ave_transparent,
                backgroundImageRes = R.drawable.ave_fondo,
                textLines = listOf("RE", "THI", "NK"),
                textScaleX = 1.0f,  // Ancho normal
                textScaleY = 1.15f,
                fontFamily = ArchivoFontFamily,
                backgroundColor = Color(0xFFF5F5F5),
                textOverlayAlpha = 15
            ),

            CardGalleryItem.MartyPosterCard3D(
                characterResId = R.drawable.ave_transparent,
                textLines = listOf("BI", "RD"),
                fontFamily = ArchivoFontFamily,          // Fuente dinámica
                containerColor = Color(0xFF42A5F5),      // Fondo melocotón
                borderColor = Color(0xFF00E5FF),         // Borde Cian Neón
                textColor = Color(0xFFFFCA28),                 // Letras iluminadas en verde
            ),
            CardGalleryItem.OrganicBirdMaskCard(
                imageRes = R.drawable.img_2
            ),
//            CardGalleryItem.OrganicBird("Birds Panel"),

            CardGalleryItem.StaticMask(R.drawable.img_2, R.drawable.mask_cube_2_padding),
            CardGalleryItem.CardMaskFromDrawable(
                imageRes = R.drawable.img_2,
                maskRes = R.drawable.mask_cube_2_padding,
                modifier = Modifier.size(300.dp)
                ),

//            CardGalleryItem.StaticMask(R.drawable.img_2, R.drawable.patter_circle_hoja),
//            CardGalleryItem.StaticMask(R.drawable.img_3, R.drawable.vector_mask_pattern),
            CardGalleryItem.DynamicMask(
                imageUrl = "https://i.pinimg.com/1200x/5f/6e/3f/5f6e3fe28c43c7002edae5eda2ff0ceb.jpg",
                maskType = 3
            ),
//            CardGalleryItem.DynamicMask(
//                imageUrl = "https://i.pinimg.com/1200x/5f/6e/3f/5f6e3fe28c43c7002edae5eda2ff0ceb.jpg",
//                maskType = 1
//            ),
//            CardGalleryItem.RedditPost(
//                title = "Jetpack Compose en 2026",
//                body = "Optimizando galerías con KISS y DRY.",
//                author = "kotlin_master",
//                subreddit = "androiddev"
//            ),
//            CardGalleryItem.Card3d(
//                badgeTitle = "Karma Champion",
//                badDescription = "Obtuviste este sticker tras desbloquear los logros de comunidad de nivel Platino."
//            ),
//            CardGalleryItem.CardGame3d(
//                gameTitle = "Space Invaders 3D",
//                gameDescription = "Tu puntuación más alta: 12,345 puntos. ¡Sigue así!"
//            ),

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
            CardGalleryItem.CharacterPosterCard(
                data = AnimeCharacterCardData(
                    titleTop = "UNICORN",
                    titleBottom = "GUNDAM",
                    characterImageRes = R.drawable.gundam_2
                ),
                modifier = Modifier
                    .height(480.dp)
                    .fillMaxWidth(),
                cardHeight = 480.dp
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
                    is CardGalleryItem.EditorialFilmPosterCard -> EditorialFilmPosterCard(
                        modifier = item.modifier,
                        backgroundImageRes = item.backgroundImageRes,
                        foregroundImageRes = item.foregroundImageRes,
                        cardIntensity = item.cardIntensity,
                        title = item.title,
                        titleFontSize = item.titleFontSize,
                        titleFontFamily = item.titleFontFamily,
                        scaleXY = item.scaleXY,
                        castLeft = item.castLeft,
                        castRight = item.castRight,
                        footer = item.footer,
                        year = item.year,
                        titleStrokeWith = item.titleStrokeWith,
                    )
                    is CardGalleryItem.ImageMaskedTextPoster -> RethinkPosterCard(
                        foregroundImageRes = item.foregroundImageRes,
                        backgroundImageRes = item.backgroundImageRes,
                        textLines = item.textLines,
                        fontFamily = item.fontFamily,
                        textScaleX = item.textScaleX,
                        textScaleY = item.textScaleY,
                        backgroundColor = item.backgroundColor,
                        textOverlayAlpha = item.textOverlayAlpha
                    )
                    is CardGalleryItem.MartyPosterCard3D -> MartyPosterCard3D(
                        characterResId = item.characterResId,
                        textLines = item.textLines,
                        fontFamily = item.fontFamily,
                        containerColor = item.containerColor,
                        borderColor = item.borderColor,
                        textColor =  item.textColor
                    )
                    is CardGalleryItem.OrganicBirdMaskCard -> OrganicBirdMaskCard(
                        imageResource = item.imageRes
                    )
                    is CardGalleryItem.OrganicBird -> GouldianFinchPanel()

                    is CardGalleryItem.DynamicMask -> DynamicTicketStudioScreen(
                        imageUrl = item.imageUrl,
                        maskType = item.maskType,
                    )

                    is CardGalleryItem.CardMaskFromDrawable -> CardMaskFromDrawable(
                        imageRes = item.imageRes,
                        maskRes = item.maskRes,
                        modifier = item.modifier
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
                    is CardGalleryItem.CharacterPosterCard ->CharacterPosterCard(
                        data = item.data,
                        modifier = item.modifier,
                        cardHeight = item.cardHeight
                    )
                    is CardGalleryItem.MartyPosterCardPerfect ->PosterFilmMaskTextImage(
                        modifier = item.modifier,
                        imageRes = item.imageRes,
                        fontFamilyTitle = item.fontFamilyTitle,
                        topWeight = item.topWeight,
                        bottomWeight = item.bottomWeight ,
                        strokeWidthDp = item.strokeWidthDp,
                        topText = item.topText,
                        headerText = item.headerText,
                        bottomText = item.bottomText,
                        lineSpacingDp = item.lineSpacingDp,
                        accentColor = item.accentColor,
                        offsetY = item.offsetY,
                        imageScale = item.imageScale

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
