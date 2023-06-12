package com.panasetskaia.countrieswithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.panasetskaia.countrieswithcompose.ui.main_screen.MainScreen
import com.panasetskaia.countrieswithcompose.ui.theme.CountriesWithComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesWithComposeTheme {
                MainScreen()
            }
        }
    }
}
