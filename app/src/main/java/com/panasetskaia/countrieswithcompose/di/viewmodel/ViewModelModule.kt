package com.panasetskaia.countrieswithcompose.di.viewmodel

import androidx.lifecycle.ViewModel
import com.panasetskaia.countrieswithcompose.ui.favourites_screen.FavouritesViewModel
import com.panasetskaia.countrieswithcompose.ui.home_screen.AllCountriesViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllCountriesViewModel::class)
    fun bindHomeScreenViewModel(impl: AllCountriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    fun bindFavouritesViewModel(impl: FavouritesViewModel): ViewModel
}