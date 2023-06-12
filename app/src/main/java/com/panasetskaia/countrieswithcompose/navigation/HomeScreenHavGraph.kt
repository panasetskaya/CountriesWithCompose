package com.panasetskaia.countrieswithcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    allCountriesScreenContent: @Composable () -> Unit,
    singleCountryScreenContent: @Composable () -> Unit
) {
    navigation(
        startDestination = Screen.AllCountries.route,
        route = Screen.Home.route
    ) {
        composable(Screen.AllCountries.route) {
            allCountriesScreenContent
        }
        composable(Screen.SingleCountry.route) {
            singleCountryScreenContent
        }
    }
}
