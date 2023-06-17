package com.panasetskaia.countrieswithcompose.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllCountriesUseCase @Inject constructor(private val repo: CountriesRepository) {

    suspend operator fun invoke(): Flow<List<Country>> {
        return repo.loadAllCountries()
    }

}