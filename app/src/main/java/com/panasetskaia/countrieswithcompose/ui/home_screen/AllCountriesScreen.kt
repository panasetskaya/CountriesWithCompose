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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.panasetskaia.countrieswithcompose.domain.Country
import com.panasetskaia.countrieswithcompose.domain.NetworkResult
import com.panasetskaia.countrieswithcompose.domain.Status
import com.panasetskaia.countrieswithcompose.ui.utils.SnackbarDelegate
import com.panasetskaia.countrieswithcompose.ui.utils.showProgressBar
import com.panasetskaia.countrieswithcompose.ui.utils.showSnackBarWithError

@Composable
fun AllCountriesScreen(
    viewModelFactory: ViewModelProvider.Factory,
    paddingValues: PaddingValues,
    snackbarDelegate: SnackbarDelegate,
    onCountryClickListener: (Country) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val viewModel: AllCountriesViewModel = viewModel(factory = viewModelFactory)

    val countriesFlow = remember(viewModel.countriesFlow, lifecycleOwner) {
        viewModel.countriesFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val countriesList: List<Country> by countriesFlow.collectAsState(initial = emptyList())

    val networkResultFlow = remember(viewModel.loadingStatusFlow, lifecycleOwner) {
        viewModel.loadingStatusFlow.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val networkResult: NetworkResult by networkResultFlow.collectAsState(initial = NetworkResult.loading())

    showList(countriesList, paddingValues, onCountryClickListener, viewModel)

    when (networkResult.status) {
        Status.SUCCESS -> {
        }
        Status.LOADING -> {
            showProgressBar()
        }
        Status.ERROR -> {
            showSnackBarWithError(snackbarDelegate, networkResult, context)
        }
    }
}

@Composable
private fun showList(
    countriesList: List<Country>,
    paddingValues: PaddingValues,
    onCountryClickListener: (Country) -> Unit,
    viewModel: AllCountriesViewModel
) {
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
            CountryCard(country, { viewModel.changeFavouriteStatus(country) }) {
                onCountryClickListener(country)
            }
        }
    }
}
