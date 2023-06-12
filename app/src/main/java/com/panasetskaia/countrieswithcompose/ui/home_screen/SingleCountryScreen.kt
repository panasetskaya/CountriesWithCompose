package com.panasetskaia.countrieswithcompose.ui.home_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.panasetskaia.countrieswithcompose.R
import com.panasetskaia.countrieswithcompose.domain.Country

@Composable
fun SingleCountryScreen(
    onBackPressed: () -> Unit
) {

    //todo: replace this dummy with the actual viewModel state as per example:
    // val viewModel: CommentsViewModel = viewModel(factory = CommentsViewModelFactory(feedPost))
    // val context = LocalContext.current
    // val screenStateAsState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    // in when block: when (val screenState = screenStateAsState.value) ...

    val country = Country(commonName = "One magnificent country",null,null, listOf(), null, null, null)
    val screenState = SingleCountryScreenState.OneCountry(country)

    when (screenState) {
        is SingleCountryScreenState.OneCountry -> {
            val country = screenState.country
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = country.commonName)
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
                        }
                    )
                }
            ) {
                Text(
                    text = "One Country",
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize(),
                    textAlign = TextAlign.Center

                )
            }
        }
        else -> {}
    }
}