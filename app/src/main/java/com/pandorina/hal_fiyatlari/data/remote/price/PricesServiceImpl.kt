package com.pandorina.hal_fiyatlari.data.remote.price

import com.pandorina.hal_fiyatlari.data.remote.price.dto.CityDto
import com.pandorina.hal_fiyatlari.data.remote.price.dto.PriceResponseDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.util.*

class PricesServiceImpl(
    private val client: HttpClient
): PricesService {
    override suspend fun getCities(): List<CityDto> {
        return client.get(PricesService.ROUTE_CITIES).body()
    }

    override suspend fun getPrices(cityId: String, date: String): PriceResponseDto {
        return client.get(PricesService.ROUTE_PRICES){
            url {
                parameters.append("cityId", cityId)
                parameters.append("date", date)
            }
        }.body()
    }

    override suspend fun getPriceDates(cityId: String): List<String> {
        return client.get(PricesService.ROUTE_DATES) {
            url {
                parameters.append("cityId", cityId)
            }
        }.body()
    }
}