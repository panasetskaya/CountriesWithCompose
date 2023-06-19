package com.panasetskaia.countrieswithcompose.domain

import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    suspend fun loadAllCountries(): Flow<List<Country>>

    suspend fun getCountryByName(commonName: String): Country?

    suspend fun changeFavouriteStatus(commonName: String)

    suspend fun getAllFavourites(): Flow<List<Country>>

    val errorStatus: Flow<NetworkResult>

}