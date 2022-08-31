package com.pandorina.hal_fiyatlari.presentation.screens.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandorina.hal_fiyatlari.domain.repository.NewsRepository
import com.pandorina.hal_fiyatlari.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel(){

    private val _newsUiStateUiState = mutableStateOf(NewsUiState())
    val newsUiStateUiState: State<NewsUiState> = _newsUiStateUiState

    val currentPage = MutableStateFlow(0)

    init {
        _newsUiStateUiState.value = NewsUiState()
        getNewsCategories()
    }

    private fun getNewsCategories(){
        viewModelScope.launch {
            newsRepository.getCategories().collectLatest {
                when(it){
                    is NetworkResult.Loading -> {
                        _newsUiStateUiState.value =
                            _newsUiStateUiState.value.copy(isLoading = true)
                    }
                    is NetworkResult.Success -> {
                        _newsUiStateUiState.value =
                            _newsUiStateUiState.value.copy(isLoading = false, categories = it.data)
                        viewModelScope.launch {
                            currentPage.collectLatest { currentPage ->
                                getNews(_newsUiStateUiState.value.categories?.get(currentPage)?.id!!)
                            }
                        }
                    }
                    is NetworkResult.Error -> {
                        _newsUiStateUiState.value =
                            _newsUiStateUiState.value.copy(isLoading = false, error = it.errorMessage)
                    }
                }
            }
        }
    }

    private fun getNews(categoryId: Int){
        viewModelScope.launch {
            newsRepository.getNews(categoryId).collectLatest {
                when(it){
                    is NetworkResult.Loading -> {
                        _newsUiStateUiState.value =
                            _newsUiStateUiState.value.copy(isLoading = true)
                    }
                    is NetworkResult.Success -> {
                        _newsUiStateUiState.value =
                            _newsUiStateUiState.value.copy(isLoading = false, news = it.data)
                    }
                    is NetworkResult.Error -> {
                        _newsUiStateUiState.value =
                            _newsUiStateUiState.value.copy(isLoading = false, error = it.errorMessage)
                    }
                }
            }
        }
    }
}