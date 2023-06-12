package com.panasetskaia.countrieswithcompose.domain

import javax.inject.Inject

class LoadAllCountriesUseCase @Inject constructor(private val repo: CountriesRepository) {

    suspend operator fun invoke(): NetworkResult<List<Country>> {
        return repo.loadAllCountries()
    }

}