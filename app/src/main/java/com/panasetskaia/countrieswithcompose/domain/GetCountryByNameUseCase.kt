package com.panasetskaia.countrieswithcompose.domain

class GetCountryByNameUseCase (private val repo: CountriesRepository) {

    suspend operator fun invoke(commonName: String): Country? {
        return repo.getCountryByName(commonName)
    }

}