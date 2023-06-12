package com.panasetskaia.countrieswithcompose.ui.favourites_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FavouritesScreen() {
    Text(
        text = "Favourites",
        modifier = androidx.compose.ui.Modifier
            .padding(8.dp)
            .fillMaxSize(),
        textAlign = TextAlign.Center
    )
}