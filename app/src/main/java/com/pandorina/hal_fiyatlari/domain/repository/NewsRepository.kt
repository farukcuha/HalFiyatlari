package com.pandorina.hal_fiyatlari.domain.repository

import com.pandorina.hal_fiyatlari.domain.model.news.Category
import com.pandorina.hal_fiyatlari.domain.model.news.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getCategories(): Flow<Result<List<Category>>>

    suspend fun getNews(categoryId: Int?): Flow<Result<List<News>>>
}