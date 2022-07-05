package com.pandorina.hal_fiyatlari.data.remote.news

import com.pandorina.hal_fiyatlari.data.remote.news.dto.CategoryDto
import com.pandorina.hal_fiyatlari.domain.model.news.News
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class NewsServiceImpl(
    private val client: HttpClient
) : NewsService {

    override suspend fun getCategories(): List<CategoryDto> {
        return client.get(NewsService.ROUTE_CATEGORIES).body()
    }

    override suspend fun getNews(categoryId: Int): List<News> {
        return client.get(NewsService.ROUTE_NEWS){
            url {
                parameters.append("categoryId", categoryId.toString())
            }
        }.body()
    }
}