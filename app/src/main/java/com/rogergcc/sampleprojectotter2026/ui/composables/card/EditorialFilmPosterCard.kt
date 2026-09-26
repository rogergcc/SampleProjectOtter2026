package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import com.rogergcc.sampleprojectotter2026.R
import com.rogergcc.sampleprojectotter2026.ui.theme.AppDimens
import com.rogergcc.sampleprojectotter2026.ui.theme.OswaldFontFamily
import com.rogergcc.sampleprojectotter2026.ui.theme.SampleProjectOtter2026Theme
import com.rogergcc.sampleprojectotter2026.ui.theme.darken

/**
 * Editorial poster intended for character cutouts and typography-led film artwork.
 *
 * The image is deliberately independent from the title so the same composition can
 * be used for Leon, Breaking Bad, or another film without duplicating the layout.
 */
@Composable
fun EditorialFilmPosterCard(
    backgroundImageRes: Int,
    foregroundImageRes: Int,
    title: String,
    modifier: Modifier = Modifier,
    castLeft: String = "",
    castRight: String = "",
    footer: String = "",
    year: String = "",
    accentColor: Color = Color(0xFFA51E24),
    backgroundColor: Color = Color(0xFFB9B9B9),
    titleFontFamily: FontFamily = OswaldFontFamily,
    scaleXY: Pair<Float, Float> = 1f to 1f,
    titleStrokeWith: Float = 1.2f,
    titleFontSize: TextUnit = 92.sp,
    foregroundScale: Float = 1f,
    cardIntensity: Float = 0.5f,
    backgroundColorFilter: ColorFilter? = ColorFilter.colorMatrix(
        ColorMatrix().apply { setToSaturation(cardIntensity) }
    ),
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.75f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Image(
                painter = painterResource(id = backgroundImageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = backgroundColorFilter,
                modifier = Modifier.fillMaxSize()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
            ) {
                PosterMetaText(
                    text = castLeft,
                    color = accentColor,
                    modifier = Modifier.padding(top = 14.dp, start = 14.dp)
                )
                PosterMetaText(
                    text = castRight,
                    color = accentColor,
                    textAlign = TextAlign.End,
                    modifier = Modifier.padding(top = 14.dp, end = 14.dp)
                )
            }

            // The title is behind the transparent character cutout.
            Text(
                text = title,
                color = accentColor,
                fontFamily = titleFontFamily,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(scaleX = scaleXY.first, scaleY = scaleXY.second)
                    .align(Alignment.Center)
                    .alpha(0.92f)
            )

            Image(
                painter = painterResource(id = foregroundImageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        scaleX = foregroundScale
                        scaleY = foregroundScale
                    }
            )

            Text(
                text = title,
                color = accentColor,
                fontFamily = titleFontFamily,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = TextStyle(drawStyle = Stroke(width = titleStrokeWith)),
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(scaleX = scaleXY.first, scaleY = scaleXY.second)
                    .align(Alignment.Center)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 14.dp, end = 14.dp, bottom = 14.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (footer.isNotBlank()) {
                    Text(
                        text = footer,
                        color = accentColor,
                        fontFamily = titleFontFamily,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        style = TextStyle(lineHeight = 9.sp),
                        modifier = Modifier
                            .widthIn(max = 200.dp)
                            .fillMaxWidth()
                            .border(width = 0.5.dp, color = accentColor, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp )

                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }

                if (year.isNotBlank()) {
                    Text(
                        text = year,
                        color = accentColor,
                        fontFamily = titleFontFamily,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .scale(scaleX = scaleXY.first/(1.2f), scaleY = scaleXY.second/3)

                    )
                }
            }
        }
    }
}

@Composable
private fun PosterMetaText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    textAlign: TextAlign = TextAlign.Start,
) {
    if (text.isNotBlank()) {
        Text(
            text = text,
            fontFamily = OswaldFontFamily,
            color = color.darken(0.7f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign,
//            style = TextStyle(
//                lineHeight = 10.sp,
//                color = Color(0xFF000000),
//
//            ),
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EditorialFilmPosterCardPreview() {
    SampleProjectOtter2026Theme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF202020))
                .padding(AppDimens.PaddingLarge),
            contentAlignment = Alignment.Center
        ) {
            EditorialFilmPosterCard(
                backgroundImageRes = R.drawable.leon_complete,
                foregroundImageRes = R.drawable.leon_sameposition,
                cardIntensity = 0.9f,
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
            )
        }
    }
}
