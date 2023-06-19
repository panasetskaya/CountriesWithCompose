package com.panasetskaia.countrieswithcompose.ui.favourites_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.panasetskaia.countrieswithcompose.R
import com.panasetskaia.countrieswithcompose.domain.Country

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun FavouriteCard(
    country: Country,
    showSelection: Boolean,
    onClickListener: (Country) -> Unit,
    changeCountrySelection: () -> Unit,
    onLongClickListener: (Country) -> Unit
) {
    //todo change Image content scale according to orientation
    Card(
        Modifier
            .padding(8.dp, 8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onClickListener(country)
                    },
                    onLongPress = {
                        onLongClickListener(country)
                    }
                )
            },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                model = country.flagUrl,
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.country_flag_desc),
                modifier = Modifier
                    .weight(1.5F)
                    .height(56.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Text(
                text = country.commonName.uppercase(),
                modifier = Modifier
                    .padding(4.dp, 16.dp)
                    .weight(4F)
                    .fillMaxSize(),
                textAlign = TextAlign.Center,
            )
            if (showSelection) {
                RadioButton(
                    selected = country.isSelected,
                    onClick = { changeCountrySelection() },
                    modifier = Modifier
                        .height(25.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
