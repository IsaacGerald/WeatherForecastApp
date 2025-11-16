package com.senri.weatherforecastapp.presentation.ui.screens.components

import android.graphics.Color
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
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
import com.touchlab.kampkit.ui.global_components.shimmer.shimmerEffect

@Composable
fun HourlyWeatherItem(
    weatherItem: WeatherItem
) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = weatherItem.dt?.toLong()?.toHourOnly12Hour() ?: "",
            style = MaterialTheme.typography.labelMedium,
            color = androidx.compose.ui.graphics.Color.White
        )


        ImageFromURLWithPlaceHolder(
            modifier = Modifier.size(48.dp),
            imageUrl = "${weatherItem.weather?.firstOrNull()?.icon}",
            placeholder = R.drawable.rain_ic,
            contentDescription = stringResource(R.string.weather),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "${weatherItem.main?.temp ?: 0.0} F°",
            style = MaterialTheme.typography.labelSmall,
            color = androidx.compose.ui.graphics.Color.White
        )
    }
}

@Composable
fun HourlyShimmerWeatherItem(
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 2.dp)) {

        Text(
            modifier = Modifier.width(80.dp)
                .shimmerEffect(),
            text = "",
            style = MaterialTheme.typography.labelMedium,
            color = androidx.compose.ui.graphics.Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier.size(48.dp)
                .shimmerEffect(),

        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.width(80.dp)
                .shimmerEffect(),
            text = "",
            style = MaterialTheme.typography.labelSmall,
            color = androidx.compose.ui.graphics.Color.White
        )
    }
}

@Preview
@Composable
fun HourlyWeatherItemPreview() {
    HourlyWeatherItem(weatherItem = WeatherItem())
}