package com.pandorina.hal_fiyatlari.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pandorina.hal_fiyatlari.presentation.component.WebViewScreen
import com.pandorina.hal_fiyatlari.presentation.screens.home.HomeScreen
import com.pandorina.hal_fiyatlari.presentation.screens.currency.CurrencyScreen
import com.pandorina.hal_fiyatlari.presentation.screens.earnings.AddEarningScreen
import com.pandorina.hal_fiyatlari.presentation.screens.earnings.EarningsScreen
import com.pandorina.hal_fiyatlari.presentation.screens.news.NewsScreen
import com.pandorina.hal_fiyatlari.presentation.screens.cities.CitiesScreen
import com.pandorina.hal_fiyatlari.presentation.screens.prices.PricesScreen

@Composable
fun MainNavController(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavGraph.Home.route){
        composable(NavGraph.Home.route){ HomeScreen(navController) }
        composable(NavGraph.Cities.route){ CitiesScreen(navController) }
        composable(
            "${NavGraph.Prices.route}/{cityId}/{title}",
            arguments = listOf(
                navArgument("cityId") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ){ PricesScreen(navController) }
        composable(NavGraph.Currency.route){ CurrencyScreen(navController) }
        composable(NavGraph.News.route){ NewsScreen(navController) }
        composable(NavGraph.Earnings.route){ EarningsScreen(navController) }
        composable(NavGraph.AddEarning.route){ AddEarningScreen(navController) }
        composable(NavGraph.WebScreen.route){ WebViewScreen(navController) }
    }
}