package com.panasetskaia.countrieswithcompose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM countrydbmodel ORDER BY commonName ASC")
    fun getCountries(): Flow<List<CountryDBModel>>

    @Query("SELECT * FROM countrydbmodel WHERE commonName == :commonName")
    suspend fun getCountryByName(commonName: String): CountryDBModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countryDBModel: CountryDBModel)

    @Query("SELECT * FROM countrydbmodel WHERE isFavourite")
    fun getAllFavourites(): Flow<List<CountryDBModel>>

}