package com.senri.weatherforecastapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senri.weatherforecastapp.R
import com.senri.weatherforecastapp.presentation.theme.PrimaryBlack
import com.senri.weatherforecastapp.presentation.theme.SecondaryBlack
import com.senri.weatherforecastapp.presentation.theme.TertiaryBlack
import com.senri.weatherforecastapp.presentation.ui.screens.components.ImageFromURLWithPlaceHolder
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastState
import okhttp3.internal.wait

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    weatherForecastState: WeatherForecastState,
    fetchWeather: () -> Unit
) {

    LaunchedEffect(Unit) {
        fetchWeather.invoke()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryBlack
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(top = 32.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = ""
                )

                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Mumbai, MA",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    )
                )

            }


            Row(modifier = Modifier.padding(32.dp)) {
                ImageFromURLWithPlaceHolder(
                    modifier = Modifier.size(180.dp),
                    imageUrl = "${weatherForecastState.currentWeather?.weather?.firstOrNull()?.icon}",
                    placeholder = R.drawable.rain_ic,
                    contentDescription = "Weather",
                    contentScale = ContentScale.Crop
                )

                Column() {
                    Text(
                        text = "64F",
                        style = TextStyle(
                            fontSize = 42.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Feels like 61",
                        style = TextStyle(fontSize = 18.sp, color = Color.White.copy(alpha = 0.3f))
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Sunny",
                        style = TextStyle(fontSize = 22.sp, color = Color.White)
                    )
                    Text(
                        text = "Today, April 25",
                        style = TextStyle(fontSize = 18.sp, color = Color.White.copy(alpha = 0.3f))
                    )


                }
            }

            Box(){

            }
        }

    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(weatherForecastState = WeatherForecastState()) { }

}