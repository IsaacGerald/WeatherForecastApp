package com.senri.weatherforecastapp.presentation.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senri.weatherforecastapp.R
import com.senri.weatherforecastapp.domain.model.WeatherItem


@Composable
fun ForecastWeatherItem(
    weatherItem: WeatherItem
){

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Today",
            style = TextStyle(fontSize = 16.sp, color = androidx.compose.ui.graphics.Color.White)
        )

        Box(
            modifier = Modifier.weight(1f),
        ) {
            ImageFromURLWithPlaceHolder(
                modifier = Modifier.size(48.dp),
                imageUrl = "${weatherItem.weather?.firstOrNull()?.icon}",
                placeholder = R.drawable.rain_ic,
                contentDescription = "Weather",
                contentScale = ContentScale.Crop
            )
        }


        Text(
            modifier = Modifier.weight(1f),
            text = "29",
            style = TextStyle(fontSize = 14.sp, color = androidx.compose.ui.graphics.Color.White)
        )
    }

}



@Preview
@Composable
fun ForecastWeatherItemPreview(){
    ForecastWeatherItem(weatherItem = WeatherItem())
}