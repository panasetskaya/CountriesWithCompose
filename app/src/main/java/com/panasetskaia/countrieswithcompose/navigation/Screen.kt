package com.panasetskaia.countrieswithcompose.navigation

sealed class Screen(
    val route: String
) {

    object Home: Screen(ROUTE_HOME)
    object AllCountries: Screen(ROUTE_ALL_COUNTRIES)
    object Favourites: Screen(ROUTE_FAVOURITES)
    object SingleCountry: Screen(ROUTE_SINGLE_COUNTRY)

    private companion object {
        const val ROUTE_ALL_COUNTRIES = "all_countries"
        const val ROUTE_FAVOURITES = "favourites"
        const val ROUTE_SINGLE_COUNTRY = "single_country"
        const val ROUTE_HOME = "home"
    }
}
