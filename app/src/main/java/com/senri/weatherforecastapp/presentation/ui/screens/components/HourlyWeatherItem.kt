package com.senri.weatherforecastapp.presentation.ui.screens.components

import android.graphics.Color
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senri.weatherforecastapp.R
import com.senri.weatherforecastapp.common.toHourOnly12Hour
import com.senri.weatherforecastapp.domain.model.WeatherItem

@Composable
fun HourlyWeatherItem(
    weatherItem: WeatherItem
) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = weatherItem.dt?.toLong()?.toHourOnly12Hour() ?: "",
            style = TextStyle(fontSize = 14.sp, color = androidx.compose.ui.graphics.Color.White)
        )

//        Text(
//            text = "${weatherItem.weather?.firstOrNull()?.main ?: ""} ",
//            style = TextStyle(fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.White)
//        )

        ImageFromURLWithPlaceHolder(
            modifier = Modifier.size(48.dp),
            imageUrl = "${weatherItem.weather?.firstOrNull()?.icon}",
            placeholder = R.drawable.rain_ic,
            contentDescription = stringResource(R.string.weather),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "${weatherItem.main?.temp ?: 0.0} F°",
            style = TextStyle(fontSize = 12.sp, color = androidx.compose.ui.graphics.Color.White)
        )
    }
}

@Preview
@Composable
fun HourlyWeatherItemPreview() {
    HourlyWeatherItem(weatherItem = WeatherItem())
}