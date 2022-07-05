package com.pandorina.hal_fiyatlari.data.remote.price

import com.pandorina.hal_fiyatlari.data.remote.price.dto.CityDto
import com.pandorina.hal_fiyatlari.data.remote.price.dto.PriceResponseDto

interface PricesService {
    companion object {
        private const val BASE_PRICE_URL = "https://www.hal_fiyatlari.herokuapp.com"

        const val ROUTE_PRICES = "$BASE_PRICE_URL/prices"
        const val ROUTE_CITIES = "$ROUTE_PRICES/cities"
        const val ROUTE_DATES = "$ROUTE_PRICES/dates"
    }

    suspend fun getPrices(cityId: String, date: String): PriceResponseDto

    suspend fun getCities(): List<CityDto>

    suspend fun getPriceDates(cityId: String): List<String>
}