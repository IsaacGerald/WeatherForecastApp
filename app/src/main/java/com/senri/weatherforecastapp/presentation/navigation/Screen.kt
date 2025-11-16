package com.senri.weatherforecastapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Detail: Screen("detail_screen")
}