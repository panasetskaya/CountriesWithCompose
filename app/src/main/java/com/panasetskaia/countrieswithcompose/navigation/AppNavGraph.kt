package com.panasetskaia.countrieswithcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.panasetskaia.countrieswithcompose.application.CountriesWithComposeApp

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    allCountriesScreenContent: @Composable () -> Unit,
    favouritesScreenContent: @Composable () -> Unit,
    singleCountryScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            allCountriesScreenContent,
            singleCountryScreenContent
        )
        composable(Screen.Favourites.route) {
            favouritesScreenContent()
        }
    }
}