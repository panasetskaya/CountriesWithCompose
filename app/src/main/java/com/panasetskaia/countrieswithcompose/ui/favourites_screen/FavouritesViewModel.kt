package com.panasetskaia.countrieswithcompose.ui.favourites_screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panasetskaia.countrieswithcompose.domain.ChangeFavouriteStatusUseCase
import com.panasetskaia.countrieswithcompose.domain.Country
import com.panasetskaia.countrieswithcompose.domain.GetAllFavouritesUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val changeFavouriteStatusUseCase: ChangeFavouriteStatusUseCase,
    private val getAllFavouritesUseCase: GetAllFavouritesUseCase
): ViewModel() {

    private val innerCash = mutableStateListOf<Country>()

    private val _favouritesFlow =
        MutableSharedFlow<List<Country>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val favouritesFlow: SharedFlow<List<Country>> = _favouritesFlow

    private val _selectionFlow = MutableStateFlow(false)
    val selectionFlow: StateFlow<Boolean> = _selectionFlow

    init {
        viewModelScope.launch {
            getAllFavouritesUseCase().collect {
                innerCash.clear()
                for (i in it) {
                    innerCash.add(i)
                }
                _favouritesFlow.emit(innerCash)
            }
        }
    }

    fun deleteSelectedFromFavourites() {
        viewModelScope.launch {
            val selected = innerCash.filter {
                it.isSelected
            }
            for (i in selected) {
                changeFavouriteStatusUseCase(i.commonName)
            }
        }
    }

    fun toggleSelectionForTheList() {
        viewModelScope.launch {
            val wasSelection = selectionFlow.value
            if (!wasSelection) {
                for (i in innerCash.indices) {
                    val item = innerCash[i]
                    innerCash[i] = item.copy(isSelected = false)
                }
                _favouritesFlow.emit(innerCash)
            }
            val newSelection = !wasSelection
            _selectionFlow.emit(newSelection)
        }
    }

    fun toggleSelection(index: Int) {
        viewModelScope.launch {
            val item = innerCash[index]
            val isSelected = item.isSelected
            if (isSelected) {
                innerCash[index] = item.copy(isSelected = false)
            } else {
                innerCash[index] = item.copy(isSelected = true)
            }
            _favouritesFlow.emit(innerCash)
        }
    }

}