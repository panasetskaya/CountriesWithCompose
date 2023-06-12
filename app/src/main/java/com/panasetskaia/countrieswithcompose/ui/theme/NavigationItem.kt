package com.panasetskaia.countrieswithcompose.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.panasetskaia.countrieswithcompose.R
import com.panasetskaia.countrieswithcompose.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
) {

    object Home : NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.navigation_item_main,
        icon = Icons.Outlined.Home
    )

    object Favourites : NavigationItem(
        screen = Screen.Favourites,
        titleResId = R.string.navigation_item_favourite,
        icon = Icons.Outlined.Favorite
    )

}