package com.pandorina.hal_fiyatlar.data.remote.news

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class NewsServiceImpl(
    private val client: HttpClient
) : NewsService {
    override suspend fun getCategories(): HttpResponse {
        return client.get(NewsService.ROUTE_CATEGORIES)
    }
    override suspend fun getNews(categoryId: Int?): HttpResponse {
        return client.get(NewsService.ROUTE_NEWS){
            url {
                parameter("category_id", categoryId)
            }
        }
    }
}