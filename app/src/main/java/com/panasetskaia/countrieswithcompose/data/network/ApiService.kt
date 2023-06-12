package com.panasetskaia.countrieswithcompose.data.network

import com.panasetskaia.countrieswithcompose.data.network.model.CountryDto
import retrofit2.http.GET

interface ApiService {

    @Throws(Exception::class)
    @GET("all")
    suspend fun getAllCountries(
    ): ArrayList<CountryDto>

}