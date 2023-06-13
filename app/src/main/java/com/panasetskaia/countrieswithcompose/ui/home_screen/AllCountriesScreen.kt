package com.panasetskaia.countrieswithcompose.ui.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.panasetskaia.countrieswithcompose.domain.Country

@Composable
fun AllCountriesScreen(
    viewModel: HomeScreenViewModel,
    paddingValues: PaddingValues,
    onCountryClickListener: (Country) -> Unit
) {
    //todo: remove that dummy for viewModel list

    val lifecycleOwner = LocalLifecycleOwner.current
    val countriesFlow = remember(viewModel.countriesFlow, lifecycleOwner) {
        viewModel.countriesFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val countriesList: List<Country> by countriesFlow.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        items(
            items = countriesList,
            key = { country: Country ->
                country.commonName
            }) { country: Country ->
            CountryCard(country, {}) {
                onCountryClickListener(country)
            }
        }
    }
}
