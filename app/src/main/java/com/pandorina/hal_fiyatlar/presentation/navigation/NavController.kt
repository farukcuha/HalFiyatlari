package com.pandorina.hal_fiyatlar.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pandorina.hal_fiyatlar.presentation.component.WebViewScreen
import com.pandorina.hal_fiyatlar.presentation.screens.home.HomeScreen
import com.pandorina.hal_fiyatlar.presentation.screens.currency.CurrencyScreen
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.AddEarningScreen
import com.pandorina.hal_fiyatlar.presentation.screens.earnings.EarningsScreen
import com.pandorina.hal_fiyatlar.presentation.screens.news.NewsScreen
import com.pandorina.hal_fiyatlar.presentation.screens.cities.CitiesScreen

@Composable
fun MainNavController(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavigationRoutes.Home.route){
        composable(NavigationRoutes.Home.route){ HomeScreen(navController) }
        composable(NavigationRoutes.Cities.route){ CitiesScreen(navController) }
        composable(NavigationRoutes.Prices.route) { WebViewScreen(navController) }
        composable(NavigationRoutes.Currency.route){ CurrencyScreen(navController) }
        composable(NavigationRoutes.News.route){ NewsScreen(navController) }
        composable(NavigationRoutes.Earnings.route){ EarningsScreen(navController) }
        composable(NavigationRoutes.AddEarning.route){ AddEarningScreen(navController) }
        composable(NavigationRoutes.WebScreen.route){ WebViewScreen(navController) }
    }
}