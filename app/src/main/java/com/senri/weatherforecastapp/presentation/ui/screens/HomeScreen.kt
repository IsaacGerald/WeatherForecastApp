package com.senri.weatherforecastapp.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    fetchWeather: () -> Unit
){

    LaunchedEffect(Unit) {
        fetchWeather.invoke()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("WeatherForecast App")
        }
    }

}

@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen() { }

}