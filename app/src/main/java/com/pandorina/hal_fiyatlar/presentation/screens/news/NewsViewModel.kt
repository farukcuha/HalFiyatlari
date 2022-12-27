package com.pandorina.hal_fiyatlar.presentation.screens.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandorina.hal_fiyatlar.domain.repository.NewsRepository
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
            _newsUiStateUiState.value =
                _newsUiStateUiState.value.copy(isLoading = true)
            newsRepository.getCategories().collectLatest { result ->
                result.onSuccess {
                    _newsUiStateUiState.value =
                        _newsUiStateUiState.value.copy(isLoading = false, categories = it)
                    viewModelScope.launch {
                        currentPage.collectLatest { currentPage ->
                            _newsUiStateUiState.value =
                                _newsUiStateUiState.value.copy(isLoading = false, news = emptyList())
                            getNews(_newsUiStateUiState.value.categories?.get(currentPage)?.id!!)
                        }
                    }
                }.onFailure {
                    _newsUiStateUiState.value =
                        _newsUiStateUiState.value.copy(isLoading = false, error = it.localizedMessage)
                }
            }
        }
    }

    private fun getNews(categoryId: Int){
        viewModelScope.launch {
            _newsUiStateUiState.value =
                _newsUiStateUiState.value.copy(isLoading = true)
            newsRepository.getNews(categoryId).collectLatest { result ->
                result.onSuccess {
                    _newsUiStateUiState.value =
                        _newsUiStateUiState.value.copy(isLoading = false, news = it)
                }.onFailure {
                    _newsUiStateUiState.value =
                        _newsUiStateUiState.value.copy(isLoading = false, error = it.localizedMessage)
                }
            }
        }
    }
}