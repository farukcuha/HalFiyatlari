package com.pandorina.hal_fiyatlari.data.remote.photo


import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class PhotoServiceImpl(
    val client: HttpClient
): PhotoService {

    override suspend fun getHomeScreenPhoto(): HttpResponse {
        return client.get(PhotoService.ROUTE_PHOTO)
    }
}