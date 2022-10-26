package com.pandorina.hal_fiyatlar.data.repostiory

import com.pandorina.hal_fiyatlar.data.remote.news.NewsService
import com.pandorina.hal_fiyatlar.data.remote.news.dto.CategoryDto
import com.pandorina.hal_fiyatlar.data.remote.news.dto.NewsDto
import com.pandorina.hal_fiyatlar.domain.model.news.Category
import com.pandorina.hal_fiyatlar.domain.model.news.News
import com.pandorina.hal_fiyatlar.domain.repository.NewsRepository
import com.pandorina.hal_fiyatlar.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class NewsRepositoryImpl(
    private val newsService: NewsService
): NewsRepository {

    override suspend fun getCategories(): Flow<Result<List<Category>>> = channelFlow {
        safeApiCall<List<CategoryDto>> {
            newsService.getCategories().call
        }.collectLatest { result ->
            result.onSuccess {
                trySend(Result.success<List<Category>>(
                    it.map { categoryDto -> categoryDto.toDomain() }
                ))
            }.onFailure {
                trySend(Result.failure<List<Category>>(it))
            }
        }
    }

    override suspend fun getNews(categoryId: Int?): Flow<Result<List<News>>> = channelFlow {
        safeApiCall<List<NewsDto>> {
            newsService.getNews(categoryId).call
        }.collectLatest { result ->
            result.onSuccess {
                trySend(Result.success<List<News>>(it.map { newsDto ->
                    newsDto.toDomain()
                }))
            }.onFailure {
                trySend(Result.failure<List<News>>(it))
            }
        }
    }
}