package com.pandorina.hal_fiyatlari.data.remote.price

import io.ktor.client.statement.*

interface PricesService {
    companion object {
        private const val ROUTE_PRICES = "/prices"
        const val ROUTE_FETCH_PRICES = "$ROUTE_PRICES/fetch"
        const val ROUTE_DATES = "$ROUTE_PRICES/dates"
    }

    suspend fun getPrices(cityId: String?, date: String?): HttpResponse

    suspend fun getPriceDates(cityId: String?): HttpResponse
}