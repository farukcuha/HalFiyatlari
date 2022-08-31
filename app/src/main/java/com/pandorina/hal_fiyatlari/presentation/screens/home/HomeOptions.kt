package com.pandorina.hal_fiyatlari.presentation.screens.home

import com.pandorina.hal_fiyatlari.R

sealed class HomeOptions(
    val title: Int, val image: Int){

    object Prices: HomeOptions(R.string.prices, R.drawable.ic_vegetables)
    object Earnings: HomeOptions(R.string.earnings, R.drawable.ic_earnings)
    object News: HomeOptions(R.string.news, R.drawable.ic_newspaper)
    object Currency: HomeOptions(R.string.currency, R.drawable.ic_currency)
}