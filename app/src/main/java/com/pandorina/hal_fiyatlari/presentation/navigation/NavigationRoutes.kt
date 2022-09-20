package com.pandorina.hal_fiyatlari.presentation.navigation

sealed class NavigationRoutes(val route: String){
    object Home: NavigationRoutes("home")
    object Cities: NavigationRoutes("cities")
    object Prices: NavigationRoutes("prices")
    object Currency: NavigationRoutes("currency")
    object News: NavigationRoutes("news")
    object WebScreen: NavigationRoutes("web_screen")
    object Earnings: NavigationRoutes("earnings")
    object AddEarning: NavigationRoutes("add_earning")
}