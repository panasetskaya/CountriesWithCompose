package com.panasetskaia.countrieswithcompose.data.mapper

import android.util.Log
import com.panasetskaia.countrieswithcompose.data.local.CountryDBModel
import com.panasetskaia.countrieswithcompose.data.network.model.CountryDto
import com.panasetskaia.countrieswithcompose.domain.Country
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CountryMapper @Inject constructor() {

    fun mapDBModelListToEntityList(dbModelList: List<CountryDBModel>): List<Country> {
        val newList: MutableList<Country> = mutableListOf()
        for (i in dbModelList) {
            val entity = mapDBModelToDomainEntity(i)
            newList.add(entity)
        }
        return newList
    }

    fun mapDtoToDBModel(dtoModel: CountryDto): CountryDBModel? {
        val languages = parseLanguages(dtoModel)
        val nameContainer = dtoModel.name
        if (nameContainer == null) return null else {
            val commonName = nameContainer.commonName ?: return null
            val officialName = dtoModel.name.officialName
            val capital = dtoModel.capital?.get(0)
            val flagUrl = dtoModel.flags?.flagPngUrl
            return CountryDBModel(
                commonName,
                officialName,
                dtoModel.subregion,
                languages,
                capital,
                dtoModel.population,
                flagUrl
            )
        }
    }

    fun mapDBModelToDomainEntity(dbModel: CountryDBModel): Country {
        return Country(
            dbModel.commonName,
            dbModel.officialName,
            dbModel.subregion,
            dbModel.languages,
            dbModel.capital,
            dbModel.population,
            dbModel.flagUrl,
            dbModel.isFavourite
        )
    }

    private fun parseLanguages(dtoModel: CountryDto): List<String> {
        val result = mutableListOf<String>()
        val languagesJsonObj = dtoModel.languages ?: return result
        val keys = languagesJsonObj.keys
        for (key in keys) {
            try {
                val language = Json.encodeToString(languagesJsonObj[key]).trim('"')
                result.add(language)
            } catch (e: Exception) {
                Log.e(
                    "MYLOG",
                    "Error occurred when parsing languagesJsonObject: ${e.message}"
                )
            }
        }
        return result
    }
}