package com.senri.weatherforecastapp.presentation.ui.screens.components

import android.service.autofill.OnClickAction
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.senri.weatherforecastapp.R
import com.senri.weatherforecastapp.common.checkDay
import com.senri.weatherforecastapp.domain.model.WeatherItem
import com.touchlab.kampkit.ui.global_components.shimmer.shimmerEffect


@Composable
fun ForecastWeatherItem(
    weatherItem: WeatherItem,
    onClickAction: () -> Unit
){
    Column(modifier = Modifier.fillMaxWidth()
        .padding(top = 4.dp)) {

    Row(
        modifier = Modifier.clickable{
            onClickAction()
        },
        verticalAlignment = Alignment.CenterVertically,
        ) {
        Box(
            modifier = Modifier
                .weight(1f) ,     // ← equal width for every item
        ) {
            Text(
                text = weatherItem.dt?.toLong()?.checkDay() ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }


        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            ImageFromURLWithPlaceHolder(
                modifier = Modifier.size(48.dp),
                imageUrl = "${weatherItem.weather?.firstOrNull()?.icon}",
                placeholder = R.drawable.rain_ic,
                contentDescription = stringResource(R.string.weather),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .weight(1f) ,     // ← equal width for every item
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${weatherItem.main?.temp ?: 0.0} F°",
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
            )
        }


            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next",
                modifier = Modifier.size(32.dp).padding(start = 16.dp),
                tint = Color.White,
            )

    }

        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp), thickness = 1.dp, color = Color.White.copy(alpha = 0.3f))

    }
}


@Composable
fun ForecastShimmerWeatherItem(
){
    Column(modifier = Modifier.fillMaxWidth()
        .padding(top = 4.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .weight(1f) ,     // ← equal width for every item
            ) {
                Text(
                    modifier = Modifier.width(100.dp).shimmerEffect(),
                    text = "",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    textAlign = TextAlign.Start
                )
            }


            Box(
                modifier = Modifier.weight(1f)
                    .size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.size(48.dp).shimmerEffect(),
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f) ,     // ← equal width for every item
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.width(100.dp).shimmerEffect(),
                    text = "",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.White,
                )
            }


        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 4.dp), thickness = 1.dp, color = Color.White.copy(alpha = 0.3f))

    }
}



@Preview
@Composable
fun ForecastWeatherItemPreview(){
    ForecastWeatherItem(weatherItem = WeatherItem()){}
}