package com.panasetskaia.countrieswithcompose.ui.favourites_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panasetskaia.countrieswithcompose.domain.ChangeFavouriteStatusUseCase
import com.panasetskaia.countrieswithcompose.domain.Country
import com.panasetskaia.countrieswithcompose.domain.GetAllFavouritesUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val changeFavouriteStatusUseCase: ChangeFavouriteStatusUseCase,
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase
): ViewModel() {

    private val _favouritesFlow =
        MutableSharedFlow<List<Country>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val favouritesFlow: SharedFlow<List<Country>> = _favouritesFlow


    init {
        viewModelScope.launch {
            getAllFavouritesUseCase().collect {
                _favouritesFlow.emit(it)
            }
        }
    }

    fun changeFavouriteStatus(country: Country) {
        viewModelScope.launch {
            changeFavouriteStatusUseCase(country.commonName)
        }

    }

}