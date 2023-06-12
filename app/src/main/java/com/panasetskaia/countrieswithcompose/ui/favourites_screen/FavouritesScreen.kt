package com.panasetskaia.countrieswithcompose.ui.favourites_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavouritesScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: FavouritesViewModel = viewModel(factory = viewModelFactory),
) {
    Text(
        text = "Favourites",
        modifier = androidx.compose.ui.Modifier
            .padding(8.dp)
            .fillMaxSize(),
        textAlign = TextAlign.Center
    )
}