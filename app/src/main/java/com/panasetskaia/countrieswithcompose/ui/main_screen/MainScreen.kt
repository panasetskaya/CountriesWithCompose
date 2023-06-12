package com.panasetskaia.countrieswithcompose.ui.main_screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.panasetskaia.countrieswithcompose.navigation.AppNavGraph
import com.panasetskaia.countrieswithcompose.navigation.rememberNavigationState
import com.panasetskaia.countrieswithcompose.ui.favourites_screen.FavouritesScreen
import com.panasetskaia.countrieswithcompose.ui.home_screen.SingleCountryScreen
import com.panasetskaia.countrieswithcompose.ui.home_screen.all_countries_screen.AllCountriesScreen
import com.panasetskaia.countrieswithcompose.ui.theme.NavigationItem

@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

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
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = MaterialTheme.colors.background
                    )
                }

            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            allCountriesScreenContent = {
                AllCountriesScreen(
                    paddingValues = paddingValues
                ) {
                    navigationState.navigateToSingleCountry()
                }
            },
            favouritesScreenContent = { FavouritesScreen() },
            singleCountryScreenContent = {
                SingleCountryScreen {
                    navigationState.navHostController.popBackStack()
                }
            }
        )
    }

}