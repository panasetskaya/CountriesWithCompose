package com.panasetskaia.countrieswithcompose.application

import android.app.Application
import com.panasetskaia.countrieswithcompose.di.DaggerAppComponent

class CountriesWithComposeApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)

    }
}