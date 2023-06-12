package com.panasetskaia.countrieswithcompose.data.repo

import android.app.Application
import com.panasetskaia.countrieswithcompose.R
import javax.inject.Inject

class CountriesResourceManager @Inject constructor(private val application: Application) {
    fun returnOfflineErrorString(): String {
        return application.applicationContext.getString(R.string.offline_error)
    }

    fun returnNetworkErrorString(): String {
        return application.applicationContext.getString(R.string.network_error)
    }
}