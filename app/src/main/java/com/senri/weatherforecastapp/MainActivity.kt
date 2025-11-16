package com.senri.weatherforecastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.senri.weatherforecastapp.presentation.navigation.NavigationGraph
import com.senri.weatherforecastapp.presentation.theme.WeatherForecastAppTheme
import com.senri.weatherforecastapp.presentation.ui.screens.HomeScreen
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastState
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherForecastAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)

                    ) {
                        NavigationGraph()
                    }
                }
            }
        }
    }
}

