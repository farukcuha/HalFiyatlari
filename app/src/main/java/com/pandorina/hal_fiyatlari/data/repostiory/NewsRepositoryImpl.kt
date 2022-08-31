package com.pandorina.hal_fiyatlari.data.repostiory

import com.pandorina.hal_fiyatlari.data.remote.news.NewsService
import com.pandorina.hal_fiyatlari.data.remote.news.dto.CategoryDto
import com.pandorina.hal_fiyatlari.data.remote.news.dto.NewsDto
import com.pandorina.hal_fiyatlari.domain.model.news.Category
import com.pandorina.hal_fiyatlari.domain.model.news.News
import com.pandorina.hal_fiyatlari.domain.repository.NewsRepository
import com.pandorina.hal_fiyatlari.presentation.screens.news.NewsUiState
import com.pandorina.hal_fiyatlari.util.NetworkResult
import com.pandorina.hal_fiyatlari.util.safeApiCall
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val newsService: NewsService
): NewsRepository {

    override suspend fun getCategories(): Flow<NetworkResult<List<Category>>> = channelFlow {
        safeApiCall<List<CategoryDto>> {
            newsService.getCategories().call
        }.collectLatest {
            when(it){
                is NetworkResult.Loading -> {
                    trySend(NetworkResult.Loading<List<Category>>())
                }
                is NetworkResult.Success -> {
                    trySend(NetworkResult.Success<List<Category>>(data = it.data?.map { categoryDto ->
                        categoryDto.toDomain()
                    } ?: emptyList()))
                }
                is NetworkResult.Error -> {
                    trySend(NetworkResult.Error<List<Category>>(errorMessage = it.errorMessage))
                }
            }
        }
    }

    override suspend fun getNews(categoryId: Int?): Flow<NetworkResult<List<News>>> = channelFlow {
        safeApiCall<List<NewsDto>> {
            newsService.getNews(categoryId).call
        }.collectLatest {
            when(it){
                is NetworkResult.Loading -> {
                    trySend(NetworkResult.Loading<List<News>>())
                }
                is NetworkResult.Success -> {
                    trySend(NetworkResult.Success<List<News>>(data = it.data?.map { newsDto ->
                        newsDto.toDomain()
                    } ?: emptyList()))
                }
                is NetworkResult.Error -> {
                    trySend(NetworkResult.Error<List<News>>(errorMessage = it.errorMessage))
                }
            }
        }
    }
}