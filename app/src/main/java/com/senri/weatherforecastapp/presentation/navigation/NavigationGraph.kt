package com.senri.weatherforecastapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senri.weatherforecastapp.domain.model.DailyForecastModel
import com.senri.weatherforecastapp.presentation.ui.screens.HomeScreen
import com.senri.weatherforecastapp.presentation.ui.screens.WeatherDetailScreen
import kotlinx.serialization.json.Json
import java.util.Date

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            "${Screen.Detail.route}/{weather}/{location}",
            arguments = listOf(
                navArgument("weather") {
                type = NavType.StringType
            },
                navArgument("location") {
                    type = NavType.StringType
                },
            )
        ) { backStackEntry ->
            val dailyForecastJson = backStackEntry.arguments?.getString("weather") ?: ""
            val locationName = backStackEntry.arguments?.getString("location") ?: ""
            val  dailyForeCastModel = try {
                Json.decodeFromString<DailyForecastModel>(dailyForecastJson)
            } catch (e: Exception) {
                DailyForecastModel("", emptyList())
            }
            WeatherDetailScreen(navController = navController, dailyForeCastModel, locationName)
        }
    }
}