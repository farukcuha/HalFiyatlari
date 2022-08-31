package com.pandorina.hal_fiyatlari.data.remote.city

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class CitiesServiceImpl(
    val client: HttpClient
): CitiesService {

    override suspend fun getCities(): HttpResponse {
        return client.get(CitiesService.ROUTE_CITIES)
    }
}