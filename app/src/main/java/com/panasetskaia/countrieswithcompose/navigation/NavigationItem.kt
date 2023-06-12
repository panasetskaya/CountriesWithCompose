package com.panasetskaia.countrieswithcompose.navigation

import com.panasetskaia.countrieswithcompose.R

sealed class NavigationItem(
    val screen: Screen,
    val iconId: Int
) {

    object Home : NavigationItem(
        screen = Screen.Home,
        iconId = R.drawable.ic_baseline_home_24
    )

    object Favourites : NavigationItem(
        screen = Screen.Favourites,
        iconId = R.drawable.ic_baseline_favorite_24
    )

}