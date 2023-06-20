package com.panasetskaia.countrieswithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.panasetskaia.countrieswithcompose.application.CountriesWithComposeApp
import com.panasetskaia.countrieswithcompose.di.viewmodel.ViewModelFactory
import com.panasetskaia.countrieswithcompose.ui.main_screen.MainScreen
import com.panasetskaia.countrieswithcompose.ui.theme.CountriesWithComposeTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val component by lazy {
        (application as CountriesWithComposeApp).component
    }

//    val viewModel by lazy {
//        ViewModelProvider(this, viewModelFactory)[HomeScreenViewModel::class.java]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            CountriesWithComposeTheme {
                MainScreen(viewModelFactory)
            }
        }
    }
}
