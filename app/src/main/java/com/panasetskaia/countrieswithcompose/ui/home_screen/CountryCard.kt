package com.panasetskaia.countrieswithcompose.ui.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.panasetskaia.countrieswithcompose.R
import com.panasetskaia.countrieswithcompose.domain.Country


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CountryCard(
    country: Country,
    onLikeClickListener: (Country) -> Unit,
    onDetailsClickListener: (Country) -> Unit,
) {
    //todo change Image content scale according to orientation
    Card(
        Modifier
            .padding(8.dp, 8.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        val iconColor = if (country.isFavourite) {
            MaterialTheme.colors.primary
        } else MaterialTheme.colors.secondary
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                model = country.flagUrl,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.country_flag_desc),
                modifier = Modifier
                    .clickable { onDetailsClickListener(country) }
                    .weight(1.5F)
                    .height(56.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            IconButton(
                onClick = { onDetailsClickListener(country) },
                modifier = Modifier
                    .padding(4.dp, 16.dp)
                    .weight(4F),
            ) {
                Text(
                    text = country.commonName.uppercase(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize()

                )
            }
            IconButton(
                onClick = { onLikeClickListener(country) },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription = stringResource(id = R.string.add_to_favourite),
                    tint = iconColor,
                    modifier = Modifier
                        .weight(0.5F)
                        .height(20.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewCard() {
    val country = Country(
        "Spain",
        null, null, listOf(), null, null, "https://flagcdn.com/w320/jo.png"
    )
    CountryCard(country = country, onLikeClickListener = {}, onDetailsClickListener = {})
}