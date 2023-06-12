package com.panasetskaia.countrieswithcompose.ui.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.panasetskaia.countrieswithcompose.domain.Country


@Composable
fun CountryCard(
    country: Country,
    onLikeClickListener: (Country) -> Unit,
    onDetailsClickListener: (Country) -> Unit,
) {
    //todo: replace .clickable with icon onclick
    Card(
        Modifier
            .padding(8.dp)
            .clickable { onDetailsClickListener(country) }) {
        Row {
//            GlideImage(
//                imageModel = { country.flagUrl },
//                modifier = Modifier.width(36.dp),
//                imageOptions = ImageOptions(
//                    contentScale = ContentScale.Crop,
//                    alignment = Alignment.Center
//                )
//            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = country.commonName)
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