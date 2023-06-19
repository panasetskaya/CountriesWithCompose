package com.panasetskaia.countrieswithcompose.ui.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panasetskaia.countrieswithcompose.domain.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val loadAllCountriesUseCase: LoadAllCountriesUseCase,
    private val changeFavouriteStatusUseCase: ChangeFavouriteStatusUseCase,
    val getErrorStatusUseCase: GetErrorStatusUseCase

) : ViewModel() {

    private val _countriesFlow =
        MutableSharedFlow<List<Country>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val countriesFlow: SharedFlow<List<Country>> = _countriesFlow

    private val _loadingStatusFlow = MutableStateFlow(NetworkResult.loading())
    val loadingStatusFlow: StateFlow<NetworkResult> = _loadingStatusFlow


    init {
        viewModelScope.launch {
            getErrorStatusUseCase().collect {
                _loadingStatusFlow.emit(it)
            }
        }
        viewModelScope.launch {
            loadAllCountriesUseCase().collect {
                _countriesFlow.emit(it)
            }
        }

    }

    fun changeFavouriteStatus(country: Country) {
        viewModelScope.launch {
            changeFavouriteStatusUseCase(country.commonName)
        }

    }

}