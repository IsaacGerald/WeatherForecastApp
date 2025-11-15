package com.senri.weatherforecastapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.senri.weatherforecastapp.presentation.theme.WeatherForecastAppTheme
import com.senri.weatherforecastapp.presentation.ui.screens.HomeScreen
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastState
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherForecastViewModel by viewModels<WeatherForecastViewmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherForecastAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding), weatherForecastState = WeatherForecastState()) {
                      weatherForecastViewModel.getWeatherForecast(
                          lat = 1.2754232230063174,
                          lon = 36.95509013322898
                      )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WeatherForecastAppTheme {
        HomeScreen(weatherForecastState = WeatherForecastState()) { }
    }
}