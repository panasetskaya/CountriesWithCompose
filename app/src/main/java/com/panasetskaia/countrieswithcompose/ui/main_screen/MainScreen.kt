package com.panasetskaia.countrieswithcompose.ui.main_screen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.panasetskaia.countrieswithcompose.navigation.AppNavGraph
import com.panasetskaia.countrieswithcompose.navigation.NavigationItem
import com.panasetskaia.countrieswithcompose.navigation.rememberNavigationState
import com.panasetskaia.countrieswithcompose.ui.favourites_screen.FavouritesScreen
import com.panasetskaia.countrieswithcompose.ui.home_screen.AllCountriesScreen
import com.panasetskaia.countrieswithcompose.ui.home_screen.SingleCountryScreen
import com.panasetskaia.countrieswithcompose.ui.utils.SnackbarDelegate

@Composable
fun MainScreen(viewModelFactory: ViewModelProvider.Factory) {
    val navigationState = rememberNavigationState()
    val scope = rememberCoroutineScope()
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val snackbarDelegate: SnackbarDelegate by lazy {
        SnackbarDelegate(
            snackbarHostState = scaffoldState.snackbarHostState,
            coroutineScope = scope
        )
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
            SnackbarHost(
                hostState = snackbarHostState
            ) {
                val backgroundColor = snackbarDelegate.snackbarBackgroundColor
                val contentColor = snackbarDelegate.snackbarContentColor
                Snackbar(
                    snackbarData = it,
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,
                    shape = RoundedCornerShape(16.dp)
                )
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            allCountriesScreenContent = {
                AllCountriesScreen(
                    viewModelFactory,
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
                    { navigationState.navHostController.popBackStack() }
                ) {
                    navigationState.navigateToSingleCountry()
                }
            },
            singleCountryScreenContent = {
                SingleCountryScreen(viewModelFactory) {
                    navigationState.navHostController.popBackStack()
                }
            }
        )
    }
}