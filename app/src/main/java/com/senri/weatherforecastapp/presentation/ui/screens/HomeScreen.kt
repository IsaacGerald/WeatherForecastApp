package com.senri.weatherforecastapp.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    weatherForecastState: WeatherForecastState,
    fetchWeather: () -> Unit
){

    LaunchedEffect(Unit) {
        fetchWeather.invoke()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row() {
                ImageFromURLWithPlaceHolder(
                    modifier = Modifier.size(40.dp),
                    imageUrl = "${weatherForecastState.currentWeather?.weather?.firstOrNull()?.icon}",
                    placeholder = -1,
                    contentDescription = "Weather"
                )
            }
        }
    }

}

@Composable
@Preview
fun HomeScreenPreview(){
    //HomeScreen() { }

}