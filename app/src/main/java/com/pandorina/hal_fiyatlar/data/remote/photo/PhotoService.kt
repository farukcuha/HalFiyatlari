package com.pandorina.hal_fiyatlar.data.remote.photo

import io.ktor.client.statement.*

interface PhotoService {
    companion object {
        const val ROUTE_PHOTO = "/photo"
    }
    suspend fun getHomeScreenPhoto(): HttpResponse
}