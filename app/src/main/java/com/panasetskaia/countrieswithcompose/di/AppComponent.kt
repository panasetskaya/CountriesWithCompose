package com.panasetskaia.countrieswithcompose.di

import android.app.Application
import com.panasetskaia.countrieswithcompose.MainActivity
import com.panasetskaia.countrieswithcompose.di.data.DataModule

import com.panasetskaia.countrieswithcompose.di.viewmodel.ViewModelModule

import dagger.BindsInstance
import dagger.Component

@CountriesScrollerScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance application: Application): AppComponent
    }

}