package com.pandorina.hal_fiyatlar.data.remote.price

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class PricesServiceImpl(
    private val client: HttpClient
): PricesService {
    override suspend fun getPrices(cityId: String?, date: String?): HttpResponse {
        return client.get("${PricesService.ROUTE_FETCH_PRICES}/$cityId"){
            url {
                parameter("date", date)
            }
        }
    }
    override suspend fun getPriceDates(cityId: String?): HttpResponse {
        return client.get("${PricesService.ROUTE_DATES}/$cityId")
    }
}