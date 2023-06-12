package com.panasetskaia.countrieswithcompose.ui.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.panasetskaia.countrieswithcompose.domain.Country
import com.panasetskaia.countrieswithcompose.domain.LoadAllCountriesUseCase
import com.panasetskaia.countrieswithcompose.domain.Status
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(private val loadAllCountriesUseCase: LoadAllCountriesUseCase): ViewModel() {

    private val _countriesFlow =
        MutableSharedFlow<List<Country>>(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val countriesFlow: SharedFlow<List<Country>> = _countriesFlow

    init {
        viewModelScope.launch {
            val networkResult = loadAllCountriesUseCase()
            when (networkResult.status) {
                Status.SUCCESS -> {
                    networkResult.data?.let {
                        Log.d("MYLOG", it.toString())
                        _countriesFlow.tryEmit(it) }
                }
                Status.ERROR -> {

                }
            }
        }
    }

}