package com.panasetskaia.countrieswithcompose.ui.favourites_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.panasetskaia.countrieswithcompose.R
import com.panasetskaia.countrieswithcompose.domain.Country
import com.panasetskaia.countrieswithcompose.ui.utils.SnackbarDelegate

@Composable
fun FavouritesScreen(
    viewModelFactory: ViewModelProvider.Factory,
    snackbarDelegate: SnackbarDelegate,
    onBackPressed: () -> Unit,
    onCountryClickListener: (Country) -> Unit
) {
    val viewModel: FavouritesViewModel = viewModel(factory = viewModelFactory)
    val lifecycleOwner = LocalLifecycleOwner.current
    val countriesFlow = remember(viewModel.favouritesFlow, lifecycleOwner) {
        viewModel.favouritesFlow.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val countriesList: List<Country> by countriesFlow.collectAsState(initial = emptyList())
    val selectionFlow = remember(viewModel.selectionFlow, lifecycleOwner) {
        viewModel.selectionFlow.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }
    val showSelection: Boolean by selectionFlow.collectAsState(initial = false)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Favourites")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPressed()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    if (showSelection) {
                        IconButton(onClick = { viewModel.deleteSelectedFromFavourites() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                                contentDescription = stringResource(id = R.string.deleteFromFav)
                            )
                        }
                    }
                }
            )
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(
                items = countriesList,
                key = { index: Int, country: Country ->
                    country.commonName
                },
            ) { index: Int, country: Country ->
                FavouriteCard(
                    country,
                    showSelection,
                    onCountryClickListener,
                    { viewModel.toggleSelection(index) }
                ) {
                    viewModel.toggleSelectionForTheList()
                }
            }
        }
    }
}