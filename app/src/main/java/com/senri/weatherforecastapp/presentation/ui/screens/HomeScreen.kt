package com.senri.weatherforecastapp.presentation.ui.screens

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.senri.weatherforecastapp.R
import com.senri.weatherforecastapp.common.getCurrentDateFormatted
import com.senri.weatherforecastapp.common.util.LocationState
import com.senri.weatherforecastapp.presentation.navigation.Screen
import com.senri.weatherforecastapp.presentation.theme.PrimaryBlack
import com.senri.weatherforecastapp.presentation.theme.TertiaryBlack
import com.senri.weatherforecastapp.presentation.ui.screens.components.ErrorScreen
import com.senri.weatherforecastapp.presentation.ui.screens.components.ForecastShimmerWeatherItem
import com.senri.weatherforecastapp.presentation.ui.screens.components.ForecastWeatherItem
import com.senri.weatherforecastapp.presentation.ui.screens.components.HourlyShimmerWeatherItem
import com.senri.weatherforecastapp.presentation.ui.screens.components.HourlyWeatherItem
import com.senri.weatherforecastapp.presentation.ui.screens.components.ImageFromURLWithPlaceHolder
import com.senri.weatherforecastapp.presentation.ui.screens.components.LocationPermissionHandler
import com.senri.weatherforecastapp.presentation.ui.screens.components.PullToRefreshBox
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastState
import com.senri.weatherforecastapp.presentation.viewmodel.WeatherForecastViewmodel
import com.touchlab.kampkit.ui.global_components.shimmer.shimmerEffect
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.internal.wait

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    weatherViewModel: WeatherForecastViewmodel = hiltViewModel(),
) {

    val weatherState by weatherViewModel.weatherForecastState.collectAsStateWithLifecycle()

    val locationState by weatherViewModel.locationState.collectAsStateWithLifecycle()
    val isTracking by weatherViewModel.isTracking.collectAsStateWithLifecycle()

    var showPermissionDialog by remember { mutableStateOf(false) }
    var shouldOpenAppSettings by remember { mutableStateOf(false) }
    var shouldShowErrorDialog by remember { mutableStateOf(false) }
    var checkLocationPermission by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }
    var location by remember { mutableStateOf<Location?>(null) }
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()




    if (checkLocationPermission) {
        LocationPermissionHandler(
            onPermissionGranted = {
                weatherViewModel.startLocationTracking()
                weatherViewModel.getWeatherForecast(location)
                showPermissionDialog = false
                checkLocationPermission = false
            },
            onPermissionDenied = {
                showPermissionDialog = true
                checkLocationPermission = false
            }
        )
    }


    LaunchedEffect(locationState) {
        when (val state = locationState) {
            is LocationState.Idle -> {

            }

            is LocationState.Loading -> {

            }

            is LocationState.Success -> {
                location = state.location
                weatherViewModel.stopLocationTracking()
                weatherViewModel.getWeatherForecast(state.location)
            }

            is LocationState.Error -> {

                Toast.makeText(context, "${state.message}. Please move your device", Toast.LENGTH_LONG)
                    .show()

            }
        }
    }



    val window = (context as Activity).window

    LaunchedEffect(Unit) {
        window.statusBarColor = PrimaryBlack.toArgb()

        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            false
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PrimaryBlack,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    modifier = Modifier.padding(16.dp),
                    contentColor = Color.White
                )
            }
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = weatherState.loading && weatherState.futureWeatherForecast.isNotEmpty(),
            onRefresh = {
                if (location != null) {
                    weatherViewModel.startLocationTracking()
                    weatherViewModel.getWeatherForecast(location)
                } else {
                    checkLocationPermission = true
                }
            },
            modifier = Modifier.padding(innerPadding)
        ) {

            if (weatherState.errorMessage.isNotBlank() && weatherState.futureWeatherForecast.isEmpty()){
                ErrorScreen(
                    modifier = Modifier,
                    message = weatherState.errorMessage,
                    onRetry = {
                        weatherViewModel.getWeatherForecast(location)
                    }
                )
            }else{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LocationComponent(weatherState)

                    TodayWeatherComponent(weatherState)

                    HourlyWeatherComponent(weatherState)

                    ForeCastWeatherComponent(weatherState, navController)
                }

                if (weatherState.errorMessage.isNotEmpty()){
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = weatherState.errorMessage,
                            actionLabel = "Ok"
                        )
                    }
                }
            }


        }


        if (showPermissionDialog) {
            AlertDialog(
                onDismissRequest = { showPermissionDialog = false },
                title = { Text("Permission Required") },
                text = { Text("Location permission is required to use this feature. Please enable it in app settings.") },
                confirmButton = {
                    Button(
                        onClick = {
                            showPermissionDialog = false
                            shouldOpenAppSettings = true
                        }
                    ) {
                        Text("Open Settings")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showPermissionDialog = false
                        (context as? Activity)?.finish()
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }

        if (shouldOpenAppSettings) {
            openAppSettings()
            shouldOpenAppSettings = false
        }

        if (shouldShowErrorDialog) {
            AlertDialog(
                onDismissRequest = {
                    shouldShowErrorDialog = false
                    errorMessage = ""
                },
                title = { Text("Error") },
                text = { Text(errorMessage) },
                confirmButton = {
                    Button(
                        onClick = {
                            shouldShowErrorDialog = false
                            errorMessage = ""
                        }
                    ) {
                        Text("Okay")
                    }
                },
//                dismissButton = {
//                    TextButton(onClick = { showRationale = false }) {
//                        Text("Cancel")
//                    }
//                }
            )
        }

    }

}

@Composable
private fun LocationComponent(weatherState: WeatherForecastState) {
    Row(
        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (weatherState.loading && weatherState.weatherResponse == null) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .shimmerEffect(),

                )

            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(80.dp)
                    .shimmerEffect(),
                text = "",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )
        } else {
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.LocationOn,
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )

            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = weatherState.weatherResponse?.name ?: "---",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )
        }

    }
}

@Composable
private fun TodayWeatherComponent(weatherState: WeatherForecastState) {

    if (weatherState.loading && weatherState.todayWeatherForecast.isEmpty()) {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .width(100.dp)
                    .shimmerEffect(),
                text = "",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(150.dp)
                    .shimmerEffect(),
                text = "",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White.copy(alpha = 0.3f)
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(100.dp)
                    .shimmerEffect(),
                text = "",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .width(150.dp)
                    .shimmerEffect(),
                text = "",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White.copy(alpha = 0.3f)
            )


        }
    } else {
        Column(
            modifier = Modifier.padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${weatherState.currentWeather?.main?.temp ?: 0.0} F°",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(
                    R.string.feels_like,
                    weatherState.currentWeather?.main?.feelsLike ?: 0.0
                ),
                style = MaterialTheme.typography.displayMedium,
                color = Color.White.copy(alpha = 0.3f)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = weatherState.currentWeather?.weather?.firstOrNull()?.main ?: "---",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(R.string.today, getCurrentDateFormatted()),
                style = MaterialTheme.typography.displayMedium,
                color = Color.White.copy(alpha = 0.3f)
            )


        }
    }


}

@Composable
private fun HourlyWeatherComponent(weatherState: WeatherForecastState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = TertiaryBlack, shape = RoundedCornerShape(8.dp))

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            if (weatherState.loading && weatherState.todayWeatherForecast.isEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .width(100.dp)
                        .shimmerEffect(),
                    text = "",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.3f))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    items(5) { item ->
                        Box(
                            modifier = Modifier
                                .fillParentMaxWidth(1f / weatherState.todayWeatherForecast.size),   // equal width
                            contentAlignment = Alignment.Center
                        ) {
                            HourlyShimmerWeatherItem()
                        }
                    }
                }

            } else if (!weatherState.loading && weatherState.todayWeatherForecast.isEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "No data found",
                    style = TextStyle(color = Color.White.copy(alpha = 0.3f)),
                    textAlign = TextAlign.Center
                )

            } else {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = stringResource(R.string.today_s_forecast),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.3f))

                if (weatherState.todayWeatherForecast.isNotEmpty()) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        items(weatherState.todayWeatherForecast) { item ->
                            Box(
                                modifier = Modifier
                                    .fillParentMaxWidth(1f / weatherState.todayWeatherForecast.size),   // equal width
                                contentAlignment = Alignment.Center
                            ) {
                                HourlyWeatherItem(item)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ForeCastWeatherComponent(
    weatherState: WeatherForecastState,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .padding(top = 24.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = TertiaryBlack, shape = RoundedCornerShape(8.dp))

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            if (weatherState.loading && weatherState.futureWeatherForecast.isEmpty()){
                Text(
                    modifier = Modifier.padding(bottom = 8.dp).width(100.dp)
                        .shimmerEffect(),
                    text = "",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.3f))
                LazyColumn(
                    modifier = Modifier
                        .height(350.dp)
                        .fillMaxWidth()
                        .padding(top = 2.dp)
                ) {
                    items(5) { item ->
                        ForecastShimmerWeatherItem()
                    }
                }
            }else if (!weatherState.loading && weatherState.futureWeatherForecast.isEmpty()){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    text = "No data found",
                    style = TextStyle(color = Color.White.copy(alpha = 0.3f)),
                    textAlign = TextAlign.Center
                )
            }else{
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = stringResource(R.string._5_days_forecast),
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                HorizontalDivider(thickness = 1.dp, color = Color.White.copy(alpha = 0.3f))

                if (weatherState.futureWeatherForecast.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .height(350.dp)
                            .fillMaxWidth()
                            .padding(top = 2.dp)
                    ) {
                        items(weatherState.futureWeatherForecast) { item ->
                            item.weatherItem.firstOrNull()?.let {
                                ForecastWeatherItem(it) {
                                    val dailyForecastJson = Json.encodeToString(item)
                                    navController.navigate("${Screen.Detail.route}/$dailyForecastJson/${weatherState.weatherResponse?.name ?: ""}")
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun openAppSettings() {
    val context = LocalContext.current
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", context.packageName, null)
    }
    context.startActivity(intent)
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current))

}