package com.panasetskaia.countrieswithcompose.ui.main_screen

import android.content.Context
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.panasetskaia.countrieswithcompose.domain.NetworkResult
import com.panasetskaia.countrieswithcompose.domain.Status
import com.panasetskaia.countrieswithcompose.navigation.AppNavGraph
import com.panasetskaia.countrieswithcompose.navigation.NavigationItem
import com.panasetskaia.countrieswithcompose.navigation.rememberNavigationState
import com.panasetskaia.countrieswithcompose.ui.favourites_screen.FavouritesScreen
import com.panasetskaia.countrieswithcompose.ui.home_screen.AllCountriesScreen
import com.panasetskaia.countrieswithcompose.ui.home_screen.HomeScreenViewModel
import com.panasetskaia.countrieswithcompose.ui.home_screen.SingleCountryScreen
import com.panasetskaia.countrieswithcompose.ui.utils.SnackbarDelegate
import com.panasetskaia.countrieswithcompose.ui.utils.SnackbarState
import com.panasetskaia.countrieswithcompose.R
import kotlinx.coroutines.launch

@Composable
fun MainScreen(viewModelFactory: ViewModelProvider.Factory) {
//    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val navigationState = rememberNavigationState()
    val scope = rememberCoroutineScope()
    val homeScreenViewModel: HomeScreenViewModel = viewModel(factory = viewModelFactory)
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val snackbarDelegate: SnackbarDelegate by lazy {
        SnackbarDelegate(snackbarHostState = scaffoldState.snackbarHostState,
                coroutineScope = scope)
    }
//    val loadingStatusFlow = remember(homeScreenViewModel.loadingStatusFlow, lifecycleOwner) {
//        homeScreenViewModel.loadingStatusFlow.flowWithLifecycle(
//            lifecycleOwner.lifecycle,
//            Lifecycle.State.STARTED
//        )
//    }
//    val loadingStatus: NetworkResult by loadingStatusFlow.collectAsState(initial = NetworkResult.loading())

    LaunchedEffect(key1 = null) {
        launch {
            homeScreenViewModel.loadingStatusFlow.collect {
                handleNetworkResult(snackbarDelegate,it, context)
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourites
                )
                items.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false
                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(painterResource(id = item.iconId), contentDescription = null)
                        },
                        selectedContentColor = MaterialTheme.colors.background,
                        unselectedContentColor = MaterialTheme.colors.secondary
                    )
                }

            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = { snackbarHostState ->
            SnackbarHost(hostState = snackbarHostState) {
                val backgroundColor = snackbarDelegate.snackbarBackgroundColor
                Snackbar(snackbarData = it, backgroundColor = backgroundColor)
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            allCountriesScreenContent = {
                AllCountriesScreen(
                    homeScreenViewModel,
                    paddingValues,
                    snackbarDelegate
                ) {
                    navigationState.navigateToSingleCountry()
                }
            },
            favouritesScreenContent = {
                FavouritesScreen(
                    viewModelFactory,
                    snackbarDelegate,
                    {navigationState.navHostController.popBackStack()}
                ) {
                    navigationState.navigateToSingleCountry()
                }
            },
            singleCountryScreenContent = {
                SingleCountryScreen(homeScreenViewModel) {
                    navigationState.navHostController.popBackStack()
                }
            }
        )
    }
}


internal fun handleNetworkResult(snackbarService: SnackbarDelegate, networkResult: NetworkResult, context: Context) {
    when (networkResult.status) {
        Status.SUCCESS -> {}
        Status.LOADING -> {

        }
        Status.ERROR -> {
            val msg = networkResult.msg ?: context.getString(R.string.smth_wrong)
            snackbarService.showSnackbar(
                SnackbarState.ERROR,
                msg
            )
        }
    }
}