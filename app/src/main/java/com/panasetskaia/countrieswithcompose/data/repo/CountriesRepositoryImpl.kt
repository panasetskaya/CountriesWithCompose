package com.panasetskaia.countrieswithcompose.data.repo

import android.util.Log
import com.panasetskaia.countrieswithcompose.data.local.CountryDao
import com.panasetskaia.countrieswithcompose.data.mapper.CountryMapper
import com.panasetskaia.countrieswithcompose.data.network.ApiService
import com.panasetskaia.countrieswithcompose.domain.CountriesRepository
import com.panasetskaia.countrieswithcompose.domain.Country
import com.panasetskaia.countrieswithcompose.domain.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.net.UnknownHostException
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: CountryMapper,
    private val dao: CountryDao,
    private val countriesResourceManager: CountriesResourceManager
) : CountriesRepository {

    private val _errorStatus = MutableStateFlow(NetworkResult.loading())
    override val errorStatus: Flow<NetworkResult>
        get() = _errorStatus

    override suspend fun loadAllCountries(): Flow<List<Country>> {
        try {
            val countryDtoList = apiService.getAllCountries()
            for (countryDto in countryDtoList) {
                val countryDBModel = mapper.mapDtoToDBModel(countryDto)
                countryDBModel?.let {
                    val exists = dao.getCountryByName(it.commonName) != null
                    if (!exists) {
                        dao.insertCountry(it)
                    }
                } ?: Log.e("MYLOG", "Null common name for dto: $countryDto")
            }
            return getCountriesFromDB()
        } catch (e: Exception) {
            Log.e("MYLOG", e.message.toString())
            _errorStatus.tryEmit(createErrorResult(e))
            return getCountriesFromDB()
        }
    }

    private fun getCountriesFromDB(): Flow<List<Country>> {
        return dao.getCountries()
            .map {
                mapper.mapDBModelListToEntityList(it)
            }
            .flowOn(Dispatchers.IO)
    }

    private fun createErrorResult(e: Exception): NetworkResult {
        val msg = if (e is UnknownHostException) {
            countriesResourceManager.returnOfflineErrorString()
        } else {
            countriesResourceManager.returnNetworkErrorString()
        }
        return NetworkResult.error(msg)
    }

    override suspend fun getCountryByName(commonName: String): Country? {
        return dao.getCountryByName(commonName)?.let { mapper.mapDBModelToDomainEntity(it) }
    }

    override suspend fun changeFavouriteStatus(commonName: String) {
        val oldCountry = dao.getCountryByName(commonName)
        oldCountry?.let {
            val newCountry = it.copy(isFavourite = !it.isFavourite)
            dao.insertCountry(newCountry)
        }
    }
}