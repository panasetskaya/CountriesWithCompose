package com.panasetskaia.countrieswithcompose.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetErrorStatusUseCase @Inject constructor(private val repo: CountriesRepository) {

    operator fun invoke(): Flow<NetworkResult> {
        return repo.errorStatus
    }

}