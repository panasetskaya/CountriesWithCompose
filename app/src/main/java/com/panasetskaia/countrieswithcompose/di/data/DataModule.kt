package com.panasetskaia.countrieswithcompose.di.data

import android.app.Application
import com.panasetskaia.countrieswithcompose.data.local.CountryDao
import com.panasetskaia.countrieswithcompose.data.local.CountryDatabase
import com.panasetskaia.countrieswithcompose.data.network.ApiFactory
import com.panasetskaia.countrieswithcompose.data.network.ApiService
import com.panasetskaia.countrieswithcompose.data.repo.CountriesRepositoryImpl
import com.panasetskaia.countrieswithcompose.di.CountriesScrollerScope
import com.panasetskaia.countrieswithcompose.domain.CountriesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @CountriesScrollerScope
    @Binds
    fun bindRepo(repoImpl: CountriesRepositoryImpl): CountriesRepository

    companion object {

        @CountriesScrollerScope
        @Provides
        fun provideApi(): ApiService {
            return ApiFactory.apiService
        }

        @CountriesScrollerScope
        @Provides
        fun provideDao(application: Application): CountryDao {
            return CountryDatabase.getInstance(application).countryDao()
        }
    }
}