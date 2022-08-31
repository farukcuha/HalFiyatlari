package com.pandorina.hal_fiyatlari.domain.repository

import com.pandorina.hal_fiyatlari.data.remote.news.dto.CategoryDto
import com.pandorina.hal_fiyatlari.data.remote.news.dto.NewsDto
import com.pandorina.hal_fiyatlari.domain.model.news.Category
import com.pandorina.hal_fiyatlari.domain.model.news.News
import com.pandorina.hal_fiyatlari.util.NetworkResult
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getCategories(): Flow<NetworkResult<List<Category>>>

    suspend fun getNews(categoryId: Int?): Flow<NetworkResult<List<News>>>
}