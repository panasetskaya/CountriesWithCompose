package com.panasetskaia.countrieswithcompose.domain

class LoadAllCountriesUseCase (val repository: CountriesRepository) {

    suspend operator fun invoke(): NetworkResult<List<Country>> {
        return repository.loadAllCountries()
    }

}