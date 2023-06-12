package com.panasetskaia.countrieswithcompose.di.viewmodel

import androidx.lifecycle.ViewModel
import com.panasetskaia.countrieswithcompose.ui.favourites_screen.FavouritesViewModel
import com.panasetskaia.countrieswithcompose.ui.home_screen.HomeScreenViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenViewModel::class)
    fun bindHomeScreenViewModel(impl: HomeScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    fun bindFavouritesViewModel(impl: FavouritesViewModel): ViewModel
}