package com.pandorina.hal_fiyatlari.presentation.navigation

sealed class NavGraph(val route: String){
    object Home: NavGraph("home")
    object Cities: NavGraph("cities")
    object Prices: NavGraph("prices")
    object Currency: NavGraph("currency")
    object News: NavGraph("news")
    object WebScreen: NavGraph("web_screen")
    object Earnings: NavGraph("earnings")
    object AddEarning: NavGraph("add_earning")
}