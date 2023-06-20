package com.panasetskaia.countrieswithcompose.ui.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panasetskaia.countrieswithcompose.domain.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllCountriesViewModel @Inject constructor(
    private val loadAllCountriesUseCase: LoadAllCountriesUseCase,
    private val changeFavouriteStatusUseCase: ChangeFavouriteStatusUseCase,
    private val getErrorStatusUseCase: GetErrorStatusUseCase

) : ViewModel() {

    private val _countriesFlow =
        MutableSharedFlow<List<Country>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val countriesFlow: SharedFlow<List<Country>> = _countriesFlow

    private val _loadingStatusFlow =
        MutableSharedFlow<NetworkResult>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val loadingStatusFlow: SharedFlow<NetworkResult> = _loadingStatusFlow


    init {
        viewModelScope.launch {
            getErrorStatusUseCase().collectLatest {
                Log.d("MYTAG", "Collected result: $it")
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