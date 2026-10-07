package com.rogergcc.sampleprojectotter2026.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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

/**
 * Editorial poster intended for character cutouts and typography-led film artwork.
 *
 * The image is deliberately independent from the title so the same composition can
 * be used for Leon, Breaking Bad, or another film without duplicating the layout.
 */
@Composable
fun EditorialFilmPosterCardbase(
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
    titleFontSize: TextUnit = 92.sp,
    foregroundScale: Float = 1.35f,
    backgroundColorFilter: ColorFilter? = ColorFilter.colorMatrix(
        ColorMatrix().apply { setToSaturation(0f) }
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
                .padding(14.dp)
        ) {
            Image(
                painter = painterResource(id = backgroundImageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                colorFilter = backgroundColorFilter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 28.dp, bottom = 28.dp)

            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PosterMetaText(text = castLeft, color = accentColor)
                PosterMetaText(
                    text = castRight,
                    color = accentColor,
                    textAlign = TextAlign.End
                )
            }


            Text(
                text = title,
                color = accentColor,
                fontFamily = titleFontFamily,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Black,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(scaleX = 1.11f, scaleY = 2f)
                    .align(Alignment.Center)
                    .alpha(0.92f)
            )
            Image(
                painter = painterResource(id = foregroundImageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 28.dp, bottom = 28.dp)
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
                fontWeight = FontWeight.Black,
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = TextStyle(drawStyle = Stroke(width = 1.2f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(scaleX = 1.11f, scaleY = 2f)
                    .align(Alignment.Center)
            )



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (footer.isNotBlank()) {
                    Text(
                        text = footer,
                        color = accentColor,
                        fontFamily = titleFontFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }

                if (year.isNotBlank()) {
                    Text(
                        text = year,
                        color = accentColor,
                        fontFamily = titleFontFamily,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun PosterMetaText(
    text: String,
    color: Color,
    textAlign: TextAlign = TextAlign.Start,
) {
    if (text.isNotBlank()) {
        Text(
            text = text,
            color = color,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign
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
            EditorialFilmPosterCardbase(
                backgroundImageRes = R.drawable.leon_complete,
                foregroundImageRes = R.drawable.leon_sameposition,
                foregroundScale = 1.35f,
                title = "LEON",
                titleFontSize= 130.sp,
                titleFontFamily = OswaldFontFamily,
                castLeft = "NATALIE PORTMAN",
                castRight = "GARY OLDMAN",
                footer = "ENGLISH-LANGUAGE FRENCH ACTION CRIME THRILLER",
                year = "1994",
                modifier = Modifier.width(300.dp),
            )
        }
    }
}
