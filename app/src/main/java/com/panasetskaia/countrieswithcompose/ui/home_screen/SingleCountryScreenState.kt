package com.panasetskaia.countrieswithcompose.ui.home_screen

import com.panasetskaia.countrieswithcompose.domain.Country

sealed class SingleCountryScreenState {

    object Initial : SingleCountryScreenState()
    data class OneCountry(val country: Country) :
        SingleCountryScreenState()

}