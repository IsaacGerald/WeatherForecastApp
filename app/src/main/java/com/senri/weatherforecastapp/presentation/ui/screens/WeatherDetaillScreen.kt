package com.senri.weatherforecastapp.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.senri.weatherforecastapp.R
import com.senri.weatherforecastapp.common.toDateDayFormat
import com.senri.weatherforecastapp.common.toMonthDayFormat
import com.senri.weatherforecastapp.domain.model.DailyForecastModel
import com.senri.weatherforecastapp.presentation.navigation.Screen
import com.senri.weatherforecastapp.presentation.theme.PrimaryBlack
import com.senri.weatherforecastapp.presentation.theme.TertiaryBlack
import com.senri.weatherforecastapp.presentation.ui.screens.components.ForecastDayItem
import com.senri.weatherforecastapp.presentation.ui.screens.components.ForecastWeatherItem
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastState
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherDetailScreen(
    navController: NavController,
    dailyForeCastModel: DailyForecastModel,
    locationName: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("",  modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), style = TextStyle(
                    color = Color.White, fontSize = 16.sp
                ), textAlign = TextAlign.Center) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() },
                        modifier = Modifier.size(40.dp)
                        ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryBlack
                )
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryBlack
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)
            .verticalScroll(rememberScrollState())) {

            LocationComponent(locationName = locationName )

            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                text = dailyForeCastModel.date.toDateDayFormat(),
                style = TextStyle(
                    fontSize = 42.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .padding(horizontal = 16.dp)
                    .background(color = TertiaryBlack, shape = RoundedCornerShape(8.dp))

            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 10.dp),
                        text = stringResource(R.string.hourly_forecast),
                        style = TextStyle(fontSize = 14.sp, color = Color.White)
                    )

                    HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.3f))
                    LazyColumn(
                        modifier = Modifier
                            .height(500.dp)
                            .fillMaxWidth()
                            .padding(top = 2.dp)
                    ) {
                        items(dailyForeCastModel.weatherItem) { item ->
                            ForecastDayItem(item)

                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun LocationComponent(locationName: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.LocationOn,
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White)
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = locationName,
            style = TextStyle(
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal
            )
        )

    }
}

@Composable
@Preview
fun WeatherDetailScreenPreview() {
    WeatherDetailScreen(
        NavController(LocalContext.current), dailyForeCastModel = DailyForecastModel(
            "", emptyList()
        ),
        locationName = ""
    )
}