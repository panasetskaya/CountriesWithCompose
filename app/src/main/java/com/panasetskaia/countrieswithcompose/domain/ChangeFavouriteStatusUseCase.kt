package com.panasetskaia.countrieswithcompose.domain

import javax.inject.Inject

class ChangeFavouriteStatusUseCase @Inject constructor(private val repo: CountriesRepository) {

    suspend operator fun invoke(commonName: String) {
        repo.changeFavouriteStatus(commonName)
    }
}