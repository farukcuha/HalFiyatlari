package com.pandorina.hal_fiyatlari.data.remote.news

import com.pandorina.hal_fiyatlari.data.remote.news.dto.CategoryDto
import com.pandorina.hal_fiyatlari.domain.model.news.News

interface NewsService {
    companion object {
        private const val BASE_URL = "https://www.hal_fiyatlari.herokuapp.com"

        const val ROUTE_NEWS = "$BASE_URL/news"
        const val ROUTE_CATEGORIES = "$ROUTE_NEWS/categories"
    }

    suspend fun getCategories(): List<CategoryDto>

    suspend fun getNews(categoryId: Int): List<News>
}