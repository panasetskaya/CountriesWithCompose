package com.panasetskaia.countrieswithcompose.ui.home_screen.all_countries_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.panasetskaia.countrieswithcompose.domain.Country

@Composable
fun AllCountriesScreen(
    paddingValues: PaddingValues,
    onCountryClickListener: (Country) -> Unit
) {
    //todo: remove that dummy for viewModel list
    val country =
        Country(commonName = "One magnificent country", null, null, listOf(), null, null, null)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
        Text(
            text = "All countries list here",
            modifier = Modifier
                .padding(8.dp)
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = { onCountryClickListener(country) }) {
            Text(text = "Check out one country")
        }
    }
}
