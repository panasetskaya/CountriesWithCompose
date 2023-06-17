package com.panasetskaia.countrieswithcompose.domain

import kotlinx.coroutines.flow.Flow

interface CountriesRepository {

    suspend fun loadAllCountries(): Flow<List<Country>>

    suspend fun getCountryByName(commonName: String): Country?

    suspend fun changeFavouriteStatus(commonName: String)

    val errorStatus: Flow<NetworkResult>

}