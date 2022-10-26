package com.pandorina.hal_fiyatlar.data.remote.city

import io.ktor.client.statement.*

interface CitiesService {

    companion object {
        const val ROUTE_CITIES = "/prices/cities"
    }

    suspend fun getCities(): HttpResponse
}