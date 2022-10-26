package com.pandorina.hal_fiyatlar.data.remote.news

import io.ktor.client.statement.*

interface NewsService {
    companion object {
        const val ROUTE_NEWS = "/news"
        const val ROUTE_CATEGORIES = "$ROUTE_NEWS/categories"
    }

    suspend fun getCategories(): HttpResponse

    suspend fun getNews(categoryId: Int?): HttpResponse
}